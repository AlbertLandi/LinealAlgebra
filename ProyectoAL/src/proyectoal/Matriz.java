/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoal;

import java.math.*;

/**
 * This class represents a matrix and provides some methods for matrix operations.
 * @author Alberto
 */
public class Matriz {

    private int x; // number of rows
    private int y; // number of columns
    private int o; // order of the matrix
    double matriz[][]; // the matrix as a 2D array of doubles
    double[][] vector; // a vector for storing intermediate results

    public Matriz() {
    }

    /**
     * Constructs a new matrix with the given dimensions and order.
     * @param x the number of rows
     * @param y the number of columns
     * @param o the order of the matrix
     */
    public Matriz(int x, int y, int o) {
        this.x = x;
        this.y = y;
        this.o = o;
        this.matriz = new double[x][y];
    }

    /**
     * Prints the matrix to the standard output.
     * @param m the matrix to print
     */
    public void imprimir(Matriz m) {
        for (int i = 0; i < m.getMatriz().length; i++) {
            for (int j = 0; j < m.getMatriz().length; j++) {
                System.out.print("[" + m.getMatriz()[i][j] + "] ");
            }
            System.out.println("");
        }
    }
    
    /**
     * Performs the Gram-Schmidt orthogonalization process on the matrix.
     * @param m the matrix to orthogonalize
     */
    public void gramSchmidt(Matriz m){
        int i, j, k, iteracion=0;
        int dim = m.getO();
        double[][] r = new double[dim][dim]; // a matrix for storing the coefficients
        double[][] v = new double[dim][dim]; // a matrix for storing the orthogonal vectors

        // copy the original matrix to v
        for (i = 0; i < dim; i++) {
            for (j = 0; j < dim; j++) {
                v[i][j] = m.getMatriz()[i][j];
            }
        }
        
        // apply the Gram-Schmidt algorithm
        for (i = 0; i < dim; i++) {
            r[i][i] = getNorma(v[i]); // compute the norm of v[i]
            for (j = 0; j < dim; j++) {
                m.getVector()[i][j] = v[i][j] / r[i][i]; // normalize v[i] and store it in m.vector[i]
            }
            for (k = i + 1; k < dim; k++) {
                r[i][k] = puntoProducto(m.getVector()[i], v[k]); // compute the dot product of m.vector[i] and v[k]
                iteracion++;
                System.out.println("Iteracion: "+ iteracion);
                for (j = 0; j < dim; j++) {
                    v[k][j] = v[k][j] - r[i][k] * m.getVector()[i][j]; // subtract the projection of v[k] onto m.vector[i] from v[k]
                    System.out.println("Λ = "+ v[k][j]);
                }
            }
        }

    }

    /**
     * Returns the norm (length) of a vector.
     * @param v the vector to compute the norm of
     * @return the norm of v as a double
     */
    public double getNorma(double[] v) {
        double total = 0;
        for (int i = 0; i < v.length; i++) {
            total += Math.sqrt(v[i] * v[i]); // add the square root of each element squared to the total
        }
        return total;
    }
    
    /**
     * Returns the dot product (scalar product) of two vectors.
     * @param vector one of the vectors to compute the dot product of
     * @param v the other vector to compute the dot product of
     * @return the dot product of vector and v as a double
     */
    public double puntoProducto(double[] vector, double[] v){
        double suma = 0;
        for (int i = 0; i < vector.length; i++) {
            suma += vector[i] * v[i]; // add the product of each pair of elements to suma   
        }
        //System.out.println(suma);
        return suma;
    }


    // This method calculates the determinant of a matrix using recursion
    public static double determinante(Matriz m, int o) {
        int det;
        Matriz c = new Matriz(o, o, o); // Create a new matrix object

        int i, j, k;
        double suma = 0;
        switch (o) {
            case 1:
                suma = m.getMatriz()[0][0]; // The determinant of a 1x1 matrix is the only element
                break;
            case 2:
                suma = ((m.getMatriz()[0][0] * m.getMatriz()[1][1]) - (m.getMatriz()[0][1] * m.getMatriz()[1][0])); // The determinant of a 2x2 matrix is ad-bc
                break;
            default:
                for (int p = 0; p < m.getMatriz().length; p++) {
                    int h = 0;
                    k = 0;
                    for (i = 1; i < m.getMatriz().length; i++) {
                        for (j = 0; j < m.getMatriz().length; j++) {
                            if (j == p) {
                                continue; // Skip the column that matches the current row
                            }
                            c.getMatriz()[h][k] = m.getMatriz()[i][j]; // Copy the remaining elements to a smaller matrix
                            k++;
                            if (k == m.getMatriz().length - 1) {
                                h++;
                                k = 0;
                            }
                        }
                    }
                    suma = suma + m.getMatriz()[0][p] * Math.pow((-1), p) * determinante(c, m.getMatriz().length - 1); // Use the formula for the determinant of an nxn matrix
                }
                break;
        }
        return suma;
    }

    /**
     * This method sets the vector attribute of a matrix object to be the transpose of the matrix attribute
     * @param m a Matriz object that represents a square matrix
     */
    public void setVectores(Matriz m) {
        for (int i = 0; i < m.getMatriz().length; i++) {
            for (int j = 0; j < m.getMatriz().length; j++) {
                // Assign the element at row i and column j of the matrix to the element at row j and column i of the vector
                m.getVector()[i][j] = m.getMatriz()[j][i];
            }
        }
    }

    /**
     * This method prints the vector attribute of a matrix object to the standard output
     * @param m a Matriz object that represents a square matrix
     */
    public void imprimirVec(Matriz m) {
        for (int i = 0; i < m.getMatriz().length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < m.getMatriz().length; j++) {
                // Assign the element at row i and column j of the matrix to the element at row j and column i of the vector
                m.getVector()[i][j] = m.getMatriz()[j][i];
                // Print the element at row i and column j of the vector
                System.out.print(m.getVector()[i][j] + "  ");
            }
            System.out.println("]");
        }
    }

    
    //Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(double[][] matriz) {
        this.matriz = matriz;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int getO() {
        return o;
    }

    public double[][] getVector() {
        return vector;
    }

    public void setVector(double[][] vector) {
        this.vector = vector;
    }

}

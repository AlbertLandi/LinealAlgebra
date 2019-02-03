/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoal;

import java.math.*;

/**
 *
 * @author Alberto
 */
public class Matriz {

    private int x;
    private int y;
    private int o;
    double matriz[][];
    double[][] vector;

    public Matriz() {
    }

    public Matriz(int x, int y, int o) {
        this.x = x;
        this.y = y;
        this.o = o;
        this.matriz = new double[x][y];
    }

    public void imprimir(Matriz m) {
        for (int i = 0; i < m.getMatriz().length; i++) {
            for (int j = 0; j < m.getMatriz().length; j++) {
                System.out.print("[" + m.getMatriz()[i][j] + "] ");
            }
            System.out.println("");
        }
    }
    
    public void gramSchmidt(Matriz m){
        int i, j, k, iteracion=0;
        int dim = m.getO();
        double[][] r = new double[dim][dim];
        double[][] v = new double[dim][dim];

        for (i = 0; i < dim; i++) {
            for (j = 0; j < dim; j++) {
                v[i][j] = m.getMatriz()[i][j];
            }
        }
        for (i = 0; i < dim; i++) {
            r[i][i] = getNorma(v[i]);
            for (j = 0; j < dim; j++) {
                m.getVector()[i][j] = v[i][j] / r[i][i];
            }
            for (k = i + 1; k < dim; k++) {
                r[i][k] = puntoProducto(m.getVector()[i], v[k]);
                iteracion++;
                System.out.println("Iteracion: "+ iteracion);
                for (j = 0; j < dim; j++) {
                    v[k][j] = v[k][j] - r[i][k] * m.getVector()[i][j];
                    System.out.println("Λ = "+ v[k][j]);
                }
            }
        }

    }

    public double getNorma(double[] v) {
        double total = 0;
        for (int i = 0; i < v.length; i++) {
            total += Math.sqrt(v[i] * v[i]);
        }
        return total;
    }
    
    public double puntoProducto(double[] vector, double[] v){
        double suma = 0;
        for (int i = 0; i < vector.length; i++) {
            suma += vector[i] * v[i];    
        }
        //System.out.println(suma);
        return suma;
    }

    public static double determinante(Matriz m, int o) {
        int det;
        Matriz c = new Matriz(o, o, o);

        int i, j, k;
        double suma = 0;
        switch (o) {
            case 1:
                suma = m.getMatriz()[0][0];
                break;
            case 2:
                suma = ((m.getMatriz()[0][0] * m.getMatriz()[1][1]) - (m.getMatriz()[0][1] * m.getMatriz()[1][0]));
                break;
            default:
                for (int p = 0; p < m.getMatriz().length; p++) {
                    int h = 0;
                    k = 0;
                    for (i = 1; i < m.getMatriz().length; i++) {
                        for (j = 0; j < m.getMatriz().length; j++) {
                            if (j == p) {
                                continue;
                            }
                            c.getMatriz()[h][k] = m.getMatriz()[i][j];
                            k++;
                            if (k == m.getMatriz().length - 1) {
                                h++;
                                k = 0;
                            }
                        }
                    }
                    suma = suma + m.getMatriz()[0][p] * Math.pow((-1), p) * determinante(c, m.getMatriz().length - 1);
                }
                break;
        }
        return suma;
    }

    public void setVectores(Matriz m) {
        for (int i = 0; i < m.getMatriz().length; i++) {
            for (int j = 0; j < m.getMatriz().length; j++) {
                m.getVector()[i][j] = m.getMatriz()[j][i];
            }
        }
    }

    public void imprimirVec(Matriz m) {
        for (int i = 0; i < m.getMatriz().length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < m.getMatriz().length; j++) {
                m.getVector()[i][j] = m.getMatriz()[j][i];
                System.out.print(m.getVector()[i][j] + "  ");
            }
            System.out.println("]");
        }
    }
    
    
    

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

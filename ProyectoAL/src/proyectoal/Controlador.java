/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoal;

import java.util.*;
/**
 * This class is a controller for the matrix operations.
 * It uses the Matriz class to create and manipulate matrices.
 * It also uses the Scanner class to get user input.
 * It implements the Gram-Schmidt algorithm to orthogonalize a matrix.
 * @author Alberto
 */
public class Controlador {
    
    private int x, y, o; // x and y are the dimensions of the matrix, o is the order of the determinant
    private double det; // det is the determinant of the matrix
    Scanner sc = new Scanner(System.in); // sc is a scanner object to get user input
    private Matriz m = new Matriz(); // m is a Matriz object to perform matrix operations
    private Matriz A; // A is the matrix to be orthogonalized
    private Matriz Q; // Q is the orthogonal matrix obtained from A
    private Matriz QT; // QT is the transpose of Q
    private double[][] vec; // vec is a 2D array to store the vectors of A

    public void inicio() {
        System.out.println("Filas y columnas (Matriz cuadrada): "); // print a message to ask for the size of the matrix
        x = sc.nextInt(); // get the number of rows from user input
        y = x; // set the number of columns equal to the number of rows (square matrix)
        o = x; // set the order of the determinant equal to the number of rows
        A = new Matriz(x, y, o); // create a new Matriz object with the given dimensions and order
        System.out.println("Ingrese el contenido de la matriz: "); // print a message to ask for the content of the matrix
        for (int i = 0; i < A.getMatriz().length; i++) { // loop through the rows of A
            for (int j = 0; j < A.getMatriz().length; j++) { // loop through the columns of A
                A.getMatriz()[i][j] = sc.nextInt(); // get each element of A from user input
            }
        }
        m.imprimir(A); // print A using the imprimir method of m
        det = m.determinante(A, A.getO()); // calculate the determinant of A using the determinante method of m
        System.out.println("El determinante es: " + det); // print the determinant value
        if (det == 0) { // check if the determinant is zero
            System.out.println("La matriz no es ortoganizable."); // print a message that the matrix is not orthogonalizable
            System.exit(0); // exit the program
        }
        vec = new double[A.getX()][A.getY()]; // create a new 2D array with the same size as A
        A.setVector(vec); // set the vector attribute of A to vec
        m.setVectores(A); // set each row of vec to be a vector of A using the setVectores method of m
        m.imprimirVec(A); // print vec using the imprimirVec method of m
        m.gramSchmidt(A); // apply the Gram-Schmidt algorithm to A using the gramSchmidt method of m
    }

}


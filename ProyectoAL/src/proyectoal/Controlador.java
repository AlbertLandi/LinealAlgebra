/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoal;


import java.util.*;
/**
 *
 * @author Alberto
 */
public class Controlador {
    
    private int x, y, o;
    private double det;
    Scanner sc = new Scanner(System.in);
    private Matriz m = new Matriz();
    private Matriz A;
    private Matriz Q;
    private Matriz QT;
    private double[][] vec;

    public void inicio() {
        System.out.println("Filas y columnas (Matriz cuadrada): ");
        x = sc.nextInt();
        y = x;
        o = x;
        A = new Matriz(x, y, o);
        System.out.println("Ingrese el contenido de la matriz: ");
        for (int i = 0; i < A.getMatriz().length; i++) {
            for (int j = 0; j < A.getMatriz().length; j++) {
                A.getMatriz()[i][j] = sc.nextInt();
            }
        }
        m.imprimir(A);
        det = m.determinante(A, A.getO());
        System.out.println("El determinante es: " + det);
        if (det == 0) {
            System.out.println("La matriz no es ortoganizable.");
            System.exit(0);
        }
        vec = new double[A.getX()][A.getY()];
        A.setVector(vec);
        m.setVectores(A);
        m.imprimirVec(A);
        m.gramSchmidt(A);
    }

}

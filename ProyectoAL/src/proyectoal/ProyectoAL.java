/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoal;

/**
 * This class is the main class of the project.
 * It creates and runs a Controlador object to perform matrix operations.
 * It uses the Controlador class to access its methods and attributes.
 * @author Alberto
 */
public class ProyectoAL {

    /**
     * This is the main method of the class.
     * It takes an array of strings as arguments from the command line.
     * It creates a new Controlador object and calls its inicio method.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controlador c = new Controlador(); // create a new Controlador object c
        c.inicio(); // call the inicio method of c to start the matrix operations
    }
    
}


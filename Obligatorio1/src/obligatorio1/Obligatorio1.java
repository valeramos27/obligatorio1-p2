
package obligatorio1;

import java.util.Scanner;


public class Obligatorio1 {


    public static void main(String[] args) {

       Scanner in = new Scanner(System.in);

        Tablero t = new Tablero();

        t.cargarManual(in);   // ← se lo pasás acá

        t.mostrar();
    }
}
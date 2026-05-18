/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package obligatorio1;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Obligatorio1 {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();

        System.out.println("Trabajo desarrollado por: Aitana Alvarez (340201) y Valentina Ramos (224347)");

        String opcion = "";
        while (!opcion.equals("f")) {
            mostrarMenu();
            opcion = in.nextLine().trim().toLowerCase();

            switch (opcion) {
                case "a":
                    sistema.registrarTester(in);
                    break;
                case "b":
                    sistema.registrarMatriz(in);
                    break;
                case "c":
                    sistema.registrarTesteo(in);
                    break;
                case "d":
                    sistema.consultaTesters(in);
                    break;
                case "e":
                    sistema.mostrarEstadisticas();
                    break;
                case "f":
                    System.out.println("Finalizado");
                    break;
                default:
                    System.out.println("Opcion invalida. Ingrese una opcion entre a y f.");
                    break;
            }
        }

        in.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("a) Registrar tester");
        System.out.println("b) Registrar matriz actual del juego");
        System.out.println("c) Registrar testeo");
        System.out.println("d) Consulta de testers");
        System.out.println("e) Estadisticas");
        System.out.println("f) Terminar el programa");
        System.out.println("------------------------------");
        System.out.print("Ingrese una opcion: ");
    }
}
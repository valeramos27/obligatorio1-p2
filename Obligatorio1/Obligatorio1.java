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
        // Configuracion UTF-8 (sugerencia de la letra)
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        Scanner in = new Scanner(System.in);
        Sistema sistema = new Sistema();

        System.out.println("Trabajo desarrollado por: Aitana Alvarez (340201) y Valentina Ramos (224347)");

        String opcion = "";
        while (!opcion.equals("6")) {
            mostrarMenu();
            opcion = in.next().toLowerCase();

            switch (opcion) {
                case "1":
                    sistema.registrarTester(in);
                    break;
                case "2":
                    sistema.registrarMatriz(in);
                    break;
                case "3":
                    sistema.registrarTesteo(in);
                    break;
                case "4":
                    sistema.consultaTesters(in);
                    break;
                case "5":
                    sistema.mostrarEstadisticas();
                    break;
                case "6":
                    System.out.println("Finalizado");
                    break;
                default:
                    System.out.println("Opcion invalida. Ingrese un numero entre '1' y '6'");
                    break;
            }
        }

        in.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("1) Registrar tester");
        System.out.println("2) Registrar matriz actual del juego");
        System.out.println("3) Registrar testeo");
        System.out.println("4) Consulta de testers");
        System.out.println("5) Estadisticas");
        System.out.println("6) Terminar el programa");
        System.out.println("------------------------------");
        System.out.print("Ingrese una opcion: ");
    }
}
/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

//comentario etcetera
package obligatorio1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Tester> listaTesters;
    private Tablero tablero;

    public Sistema() {
        this.listaTesters = new ArrayList<>();
        this.tablero = new Tablero();
        this.tablero.cargarPorDefecto();
    }

    public ArrayList<Tester> getListaTesters() {
        return listaTesters;
    }

    public Tablero getTablero() {
        return tablero;
    }

    // ============================================================
    // METODOS AUXILIARES 
    // ============================================================

    public Tester buscarTesterPorNombre(String nombre) {
        Tester encontrado = null;
        int i = 0;
        while (i < this.listaTesters.size() && encontrado == null) {
            Tester actual = this.listaTesters.get(i);
            if (actual.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = actual;
            }
            i = i + 1;
        }
        return encontrado;
    }

    // ============================================================
    // OPCIONES DEL MENU
    // ============================================================

    // Opcion 1
    public void registrarTester(Scanner in) {
            System.out.print("Ingrese el nombre del tester: ");
            String nombre = in.next();

            // Validar que el nombre no este repetido
            while (this.buscarTesterPorNombre(nombre) != null) {
                System.out.print("Ya existe un tester con ese nombre. Ingrese otro: ");
                nombre = in.next();
            }

            System.out.print("Ingrese la edad: ");
            int edad = in.nextInt();
            while (edad <= 0) {
                System.out.print("Edad invalida. Ingrese una edad mayor a 0: ");
                edad = in.nextInt();
            }

            System.out.print("Ingrese los años de experiencia: ");
            int experiencia = in.nextInt();
            while (experiencia < 0) {
                System.out.print("Experiencia invalida. Ingrese un valor mayor o igual a 0: ");
                experiencia = in.nextInt();
            }

            Tester nuevo = new Tester(nombre, edad, experiencia);
            this.listaTesters.add(nuevo);

            System.out.println("Tester registrado correctamente:");
            System.out.println(nuevo);     
    }

    // Opcion 2
    public void registrarMatriz(Scanner in) {
        // TODO: implementar
        System.out.println("(opcion B: registrar matriz - pendiente)");
    }

    // Opcion 3
    public void registrarTesteo(Scanner in) {
        // TODO: implementar
        System.out.println("(opcion C: registrar testeo - pendiente)");
    }

    // Opcion 4
    public void consultaTesters(Scanner in) {
        // TODO: implementar
        System.out.println("(opcion D: consulta de testers - pendiente)");
    }

    // Opcion 5
    public void mostrarEstadisticas() {
        // TODO: implementar
        System.out.println("(opcion E: estadisticas - pendiente)");
    }
}
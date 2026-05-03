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
    public ArrayList<Tester> obtenerTestersOrdenadosPorNombre() {
    ArrayList<Tester> copia = new ArrayList<>(this.listaTesters);

    Collections.sort(copia, new java.util.Comparator<Tester>() {
        @Override
        public int compare(Tester tester1, Tester tester2) {
            return tester1.getNombre().compareToIgnoreCase(tester2.getNombre());
        }
    });

    return copia;
}

private void mostrarDetalleTesteo(Testeo testeo) {
    System.out.println();
    System.out.println("Detalle del testeo");
    System.out.println("------------------------------");
    System.out.println("Numero: " + testeo.getNumero());
    System.out.println("Tester: " + testeo.getNombreTester());
    System.out.println("Caso: " + testeo.getCaso());
    System.out.println("Parametros usados: " + testeo.getParametrosUsados());
    System.out.println("Comentario: " + testeo.getComentario());
    System.out.println("Resultado: " + testeo.getResultado());

    System.out.println();
    System.out.println("Matriz original:");
    System.out.println(this.tablero.prepararTablero(testeo.getMatrizOriginal()));

    System.out.println("Matriz resultante:");
    System.out.println(this.tablero.prepararTablero(testeo.getMatrizResultante()));
}

    // ============================================================
    // OPCIONES DEL MENU
    // ============================================================

    // Opcion 1
    public void registrarTester(Scanner in) {
        System.out.print("Ingrese el nombre del tester: ");
        String nombre = in.next();

        while (this.buscarTesterPorNombre(nombre) != null) {
            System.out.print("Ya existe un tester con ese nombre. Ingrese otro nombre: ");
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

        while (experiencia < 0 || experiencia >= edad) {
            System.out.print("Experiencia invalida. Debe ser mayor o igual a 0 y menor que la edad: ");
            experiencia = in.nextInt();
        }

        Tester nuevo = new Tester(nombre, edad, experiencia);
        this.listaTesters.add(nuevo);

        System.out.println("Tester registrado correctamente:");
        System.out.println(nuevo);
    }

    // Opcion 2
    public void registrarMatriz(Scanner in) {
        System.out.println("Matriz actual del juego:");
        this.tablero.mostrar();

        System.out.print("Desea cambiar la matriz? (S/N): ");
        String respuesta = in.next().toUpperCase();

        while (!respuesta.equals("S") && !respuesta.equals("N")) {
            System.out.print("Respuesta invalida. Ingrese S para si o N para no: ");
            respuesta = in.next().toUpperCase();
        }

        if (respuesta.equals("S")) {
            System.out.println("Seleccione una opcion:");
            System.out.println("1) Cargar matriz por defecto");
            System.out.println("2) Cargar matriz particular");
            System.out.print("Ingrese opcion: ");

            String opcion = in.next();

            while (!opcion.equals("1") && !opcion.equals("2")) {
                System.out.print("Opcion invalida. Ingrese 1 o 2: ");
                opcion = in.next();
            }

            if (opcion.equals("1")) {
                this.tablero.cargarPorDefecto();
                System.out.println("Se cargo la matriz por defecto.");
            } else {
                System.out.println("Ingrese la matriz particular.");
                System.out.println("Debe ingresar 8 filas de 10 caracteres.");
                System.out.println("Caracteres validos: B, N, V");
                this.tablero.cargarManual(in);
                System.out.println("Se cargo la matriz particular.");
            }

            System.out.println("Matriz actualizada:");
            this.tablero.mostrar();
        } else {
            System.out.println("La matriz no fue modificada.");
        }
    }

    // Opcion 3
    public void registrarTesteo(Scanner in) {
        // TODO: implementar
        System.out.println("(opcion C: registrar testeo - pendiente)");
    }

// Opcion 4
public void consultaTesters(Scanner in) {
    if (this.listaTesters.isEmpty()) {
        System.out.println("No hay testers registrados.");
    } else {
        ArrayList<Tester> testersOrdenados = this.obtenerTestersOrdenadosPorNombre();

        System.out.println("Lista de testers:");
        for (int i = 0; i < testersOrdenados.size(); i = i + 1) {
            System.out.println((i + 1) + ") " + testersOrdenados.get(i));
        }

        System.out.print("Elija un tester: ");
        int opcionTester = in.nextInt();

        while (opcionTester < 1 || opcionTester > testersOrdenados.size()) {
            System.out.print("Opcion invalida. Elija un tester valido: ");
            opcionTester = in.nextInt();
        }

        Tester testerElegido = testersOrdenados.get(opcionTester - 1);

        ArrayList<Testeo> testeosOrdenados = testerElegido.getTesteosOrdenados();

        if (testeosOrdenados.isEmpty()) {
            System.out.println("El tester elegido no tiene testeos registrados.");
        } else {
            System.out.println("Testeos realizados por " + testerElegido.getNombre() + ":");

            for (int i = 0; i < testeosOrdenados.size(); i = i + 1) {
                Testeo testeoActual = testeosOrdenados.get(i);
                System.out.println((i + 1) + ") Testeo numero " + testeoActual.getNumero()
                        + " - Caso " + testeoActual.getCaso());
            }

            System.out.print("Elija un testeo para ver el detalle: ");
            int opcionTesteo = in.nextInt();

            while (opcionTesteo < 1 || opcionTesteo > testeosOrdenados.size()) {
                System.out.print("Opcion invalida. Elija un testeo valido: ");
                opcionTesteo = in.nextInt();
            }

            Testeo testeoElegido = testeosOrdenados.get(opcionTesteo - 1);

            this.mostrarDetalleTesteo(testeoElegido);
        }
    }
}

    // Opcion 5
    public void mostrarEstadisticas() {
        // TODO: implementar
        System.out.println("(opcion E: estadisticas - pendiente)");
    }
}
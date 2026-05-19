/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package obligatorio1;

import java.util.ArrayList;
import java.util.Collections; // Lo usamos para ordenar las listas
import java.util.Scanner;

public class Sistema {
  
    
    /*CONSTANTES (dimensiones de la matriz)*/
    private static final int FILAS = 8;
    private static final int COLUMNAS = 10;
    
    /* ATRIBUTOS*/
    private ArrayList<Tester> listaTesters;
    private Tablero tablero;
    private int proximoNumeroTesteo; //contador para asignar números únicos a cada testeo nuevo

    /*CONSTRUCTOR
    Inicializa la lista de testers vacia, se crea el tablero 
    se carga la matriz por defecto e inicializa la lista de testeos con numero 1*/
       
    public Sistema() {
        this.listaTesters = new ArrayList<Tester>();
        this.tablero = new Tablero();
        this.tablero.cargarPorDefecto();
        this.proximoNumeroTesteo = 1; 
    }

    /*GETTERS*/
    
    public ArrayList<Tester> getListaTesters() {
        return listaTesters;
    }
    public Tablero getTablero() {
        return tablero;
    }
    /*METODOS DE BUSQUEDA DE TESTERS*/
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
    
    //Se trabaja sobre una copia para no alterar el orden original de la lista de testers
    public ArrayList<Tester> obtenerTestersOrdenadosPorNombre() {
        ArrayList<Tester> listaOrdenada = new ArrayList<Tester>(this.listaTesters);
        Collections.sort(listaOrdenada); //Tester implementa Comparable<Tester> asi que compara por nombre
        return listaOrdenada;
    }

    /* METODOS AUXILIARES DE INGRESO POR CONSOLA*/
    
    private String pedirTextoNoVacio(Scanner in, String mensaje) {
        String texto = "";

        while (texto.trim().isEmpty()) {
            System.out.print(mensaje);
            texto = in.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("El dato no puede quedar vacio.");
            }
        }

        return texto;
    }

    private int pedirEntero(Scanner in, String mensaje) {
        int numero = 0;
        boolean correcto = false;

        while (!correcto) {
            System.out.print(mensaje);

            if (in.hasNextInt()) {
                numero = in.nextInt();
                correcto = true;
            } else {
                System.out.println("Debe ingresar un numero entero");
            }

            in.nextLine();
        }

        return numero;
    }

    private int pedirEnteroEnRango(Scanner in, String mensaje, int minimo, int maximo) {
        int numero = pedirEntero(in, mensaje);

        while (numero < minimo || numero > maximo) {
            System.out.println("Valor invalido. Debe estar entre " + minimo + " y " + maximo);
            numero = pedirEntero(in, mensaje);
        }

        return numero;
    }

    private int pedirEnteroMinimo(Scanner in, String mensaje, int minimo) {
        int numero = pedirEntero(in, mensaje);

        while (numero < minimo) {
            System.out.println("Valor invalido. Debe ser mayor o igual a " + minimo);
            numero = pedirEntero(in, mensaje);
        }

        return numero;
    }

    private String pedirSiNo(Scanner in, String mensaje) {
        String respuesta = "";

        while (!respuesta.equals("S") && !respuesta.equals("N")) {
            System.out.print(mensaje);
            respuesta = in.nextLine().trim().toUpperCase();

            if (!respuesta.equals("S") && !respuesta.equals("N")) {
                System.out.println("Debe ingresar S o N.");
            }
        }

        return respuesta;
    }

    private char pedirColor(Scanner in) {
        String color = "";

        while (!color.equals("B") && !color.equals("N")) {
            System.out.print("Ingrese color (B/N): ");
            color = in.nextLine().trim().toUpperCase();

            if (!color.equals("B") && !color.equals("N")) {
                System.out.println("Color invalido");
            }
        }

        return color.charAt(0);
    }

    private String pedirForma(Scanner in) {
        String forma = "";

        while (!forma.equals("H") && !forma.equals("V")) {
            System.out.print("Ingrese forma del grupo (H/V): ");
            forma = in.nextLine().trim().toUpperCase();

            if (!forma.equals("H") && !forma.equals("V")) {
                System.out.println("Forma invalida");
            }
        }

        return forma;
    }

    private boolean sentidoValido(String sentido, boolean permiteDiagonales) {
        boolean valido = false;

        if (sentido.equals("N") || sentido.equals("S")
                || sentido.equals("E") || sentido.equals("O")) {
            valido = true;
        }

        if (permiteDiagonales) {
            if (sentido.equals("NE") || sentido.equals("NO")
                    || sentido.equals("SE") || sentido.equals("SO")) {
                valido = true;
            }
        }

        return valido;
    }

    private String pedirSentido(Scanner in, boolean permiteDiagonales) {
        String sentido = "";
        boolean valido = false;

        while (!valido) {
            if (permiteDiagonales) {
                System.out.print("Ingrese sentido (N, S, E, O, NE, NO, SE, SO): ");
            } else {
                System.out.print("Ingrese sentido (N, S, E, O): ");
            }

            sentido = in.nextLine().trim().toUpperCase();
            valido = sentidoValido(sentido, permiteDiagonales);

            if (!valido) {
                System.out.println("Sentido invalido");
            }
        }

        return sentido;
    }

    /* METODOS AUXILIARES DE MATRIZ*/

    //Valida que la fila ingresada por consola tenga 10 caracteres y sean B, N o V
    private boolean filaIngresadaValida(String fila) {
        boolean valida = true;
        String texto = fila.trim().toUpperCase();

        if (texto.length() != COLUMNAS) {
            valida = false;
        } else {
            int col = 0;

            while (col < COLUMNAS && valida) {
                char celda = texto.charAt(col);

                if (celda != Tablero.BLANCA
                        && celda != Tablero.NEGRA
                        && celda != Tablero.VACIO) {
                    valida = false;
                }

                col = col + 1;
            }
        }

        return valida;
    }

    private String pedirFilaMatriz(Scanner in, int numeroFila) {
        String fila = "";
        boolean valida = false;

        while (!valida) {
            System.out.print("Ingrese fila " + numeroFila + ": ");
            fila = in.nextLine().trim().toUpperCase();

            valida = filaIngresadaValida(fila);

            if (!valida) {
                System.out.println("Fila invalida. Debe tener 10 caracteres y usar solo B, N o V");
            }
        }

        return fila;
    }

    private char[][] copiarMatriz(char[][] matriz) {
        char[][] copia = new char[matriz.length][matriz[0].length];

        for (int fila = 0; fila < matriz.length; fila = fila + 1) {
            for (int col = 0; col < matriz[0].length; col = col + 1) {
                copia[fila][col] = matriz[fila][col];
            }
        }

        return copia;
    }

    /* METODOS AUXILIARES DE TESTERS Y TESTEOS*/

    private Tester elegirTester(Scanner in) {
        ArrayList<Tester> ordenados = this.obtenerTestersOrdenadosPorNombre();

        System.out.println("Lista de testers:");
        for (int i = 0; i < ordenados.size(); i = i + 1) {
            System.out.println((i + 1) + ") " + ordenados.get(i));
        }

        int opcion = pedirEnteroEnRango(in, "Elija un tester: ", 1, ordenados.size());

        return ordenados.get(opcion - 1);
    }

    private void mostrarDetalleTesteo(Testeo testeo) {
        System.out.println();
        System.out.println("Detalle del testeo");
        System.out.println("------------------------------");
        System.out.println("Numero: " + testeo.getNumero());
        System.out.println("Tester: " + testeo.getNombreTester());
        System.out.println("Caso: " + testeo.getCaso());
        System.out.println("Parametros: " + testeo.getParametrosUsados());
        System.out.println("Comentario: " + testeo.getComentario());
        System.out.println("Resultado: " + testeo.getResultado());

        System.out.println();
        System.out.println("Matriz original:");
        System.out.println(this.tablero.prepararTablero(testeo.getMatrizOriginal()));

        System.out.println("Matriz resultante:");
        System.out.println(this.tablero.prepararTablero(testeo.getMatrizResultante()));
    }

    
    // OPCION A - REGISTRAR TESTER  
    public void registrarTester(Scanner in) {
        String nombre = pedirTextoNoVacio(in, "Ingrese nombre del tester: ");

        while (this.buscarTesterPorNombre(nombre) != null) {
            System.out.println("Ya existe un tester con ese nombre.");
            nombre = pedirTextoNoVacio(in, "Ingrese otro nombre: ");
        }

        int edad = pedirEnteroMinimo(in, "Ingrese edad: ", 1);
        int experiencia = pedirEnteroEnRango(in, "Ingrese años de experiencia: ", 0, edad - 1);

        Tester nuevo = new Tester(nombre, edad, experiencia);
        this.listaTesters.add(nuevo);

        System.out.println("Tester registrado correctamente.");
        System.out.println(nuevo);
    }

    /* OPCION B - REGISTRAR MATRIZ*/
    
    public void registrarMatriz(Scanner in) {
        System.out.println("Matriz actual:");
        System.out.println(this.tablero.prepararTablero(this.tablero.getMatriz()));

        String respuesta = pedirSiNo(in, "Desea cambiar la matriza (S/N): ");

        if (respuesta.equals("S")) {
            System.out.println("1) Cargar matriz por defecto");
            System.out.println("2) Cargar matriz particular");

            int opcion = pedirEnteroEnRango(in, "Ingrese opcion: ", 1, 2);

            if (opcion == 1) {
                this.tablero.cargarPorDefecto();
                System.out.println("Se cargo la matriz por defecto.");
            } else {
                String[] filas = new String[FILAS];

                System.out.println("Ingrese 8 filas de 10 caracteres.");
                System.out.println("Caracteres validos: B, N, V.");

                for (int fila = 0; fila < FILAS; fila = fila + 1) {
                    filas[fila] = pedirFilaMatriz(in, fila + 1);
                }

                this.tablero.cargarMatriz(filas);
                System.out.println("Se cargo la matriz particular.");
            }

            System.out.println("Matriz actualizada:");
            System.out.println(this.tablero.prepararTablero(this.tablero.getMatriz()));
        } else {
            System.out.println("La matriz no fue modificada.");
        }
    }

    /* OPCION C - REGISTRAR TESTEO
    Se guarda una foto de la matriz antes y despues de ejecutar el caso de prueba*/
    
    public void registrarTesteo(Scanner in) {
        if (this.listaTesters.isEmpty()) {
            System.out.println("No hay testers registrados.");
            System.out.println("Debe registrar al menos un tester antes de testear.");
        } else {
            Tester testerElegido = elegirTester(in);

            System.out.println("Casos disponibles:");
            System.out.println("1) Contar fichas de un color en especifico");
            System.out.println("2) Realizar movimiento de una sola ficha");
            System.out.println("3) Realizar movimiento de un grupo de fichas");
            System.out.println("4) Mostrar tablero actual");
            System.out.println("5) Verificar conexión");

            int caso = pedirEnteroEnRango(in, "Ingrese caso a testear: ", 1, 5);

            System.out.print("Ingrese comentario (Opcional. Enter para omitir): ");
            String comentario = in.nextLine();

            if (comentario.trim().isEmpty()) {
                comentario = "(sin comentario)";
            }

            char[][] matrizOriginal = copiarMatriz(this.tablero.getMatriz());

            String[] datos = ejecutarCaso(caso, in);
            String parametros = datos[0];
            String resultado = datos[1];

            char[][] matrizResultante = copiarMatriz(this.tablero.getMatriz());

            System.out.println();
            System.out.println("============================================================");
            System.out.println("RESULTADO DEL TESTEO");
            System.out.println("============================================================");
            System.out.println(resultado);
            System.out.println("============================================================");

            System.out.println();
            System.out.println("Matriz original:");
            System.out.println(this.tablero.prepararTablero(matrizOriginal));

            System.out.println("Matriz resultante:");
            System.out.println(this.tablero.prepararTablero(matrizResultante));

            Testeo nuevo = new Testeo(
                    this.proximoNumeroTesteo,
                    testerElegido.getNombre(),
                    caso,
                    parametros,
                    comentario,
                    resultado,
                    matrizOriginal,
                    matrizResultante);

            testerElegido.agregarTesteo(nuevo);
            this.proximoNumeroTesteo = this.proximoNumeroTesteo + 1;

            System.out.println("Testeo numero " + nuevo.getNumero() + " registrado correctamente.");
        }
    }

    private String[] ejecutarCaso(int caso, Scanner in) {
        String[] datos = new String[2];

        switch (caso) {
            case 1:
                datos = ejecutarCaso1(in);
                break;
            case 2:
                datos = ejecutarCaso2(in);
                break;
            case 3:
                datos = ejecutarCaso3(in);
                break;
            case 4:
                datos = ejecutarCaso4();
                break;
            case 5:
                datos = ejecutarCaso5(in);
                break;
            default:
                datos[0] = "(sin parametros)";
                datos[1] = "Caso invalido";
                break;
        }

        return datos;
    }

    private String[] ejecutarCaso1(Scanner in) {
        char color = pedirColor(in);
        int cantidad = this.tablero.contarFichas(this.tablero.getMatriz(), color);

        String parametros = "color=" + color;
        String resultado = "Cantidad de fichas " + color + ": " + cantidad;

        return new String[]{parametros, resultado};
    }

    private String[] ejecutarCaso2(Scanner in) {
        char color = pedirColor(in);
        String sentido = pedirSentido(in, true);

        int fila = pedirEnteroEnRango(in, "Ingrese fila de la ficha (0-7): ", 0, 7);
        int col = pedirEnteroEnRango(in, "Ingrese columna de la ficha (0-9): ", 0, 9);
        int pasos = pedirEnteroMinimo(in, "Ingrese cantidad de pasos: ", 1);

        boolean ok = this.tablero.validarMovimientoIndividual(color, sentido, fila, col, pasos);

        String parametros = "color=" + color
                + ", sentido=" + sentido
                + ", fila=" + fila
                + ", col=" + col
                + ", pasos=" + pasos;

        String resultado;
        if (ok) {
            resultado = "Movimiento individual valido. El tablero fue modificado.";
        } else {
            resultado = "Movimiento individual invalido. El tablero no fue modificado.";
        }

        return new String[]{parametros, resultado};
    }

    private String[] ejecutarCaso3(Scanner in) {
        char color = pedirColor(in);
        String forma = pedirForma(in);
        String sentido = pedirSentido(in, false);

        if (forma.equals("H")) {
            System.out.println("Grupo horizontal: ingrese la ficha mas al oeste.");
        } else {
            System.out.println("Grupo vertical: ingrese la ficha mas al norte.");
        }

        int fila = pedirEnteroEnRango(in, "Ingrese fila del extremo (0-7): ", 0, 7);
        int col = pedirEnteroEnRango(in, "Ingrese columna del extremo (0-9): ", 0, 9);
        int tamaño = pedirEnteroEnRango(in, "Ingrese tamaño del grupo: ", 1, 10);
        int pasos = pedirEnteroMinimo(in, "Ingrese cantidad de pasos: ", 1);

        boolean ok = this.tablero.validarMovimientoEnGrupo(color, forma, sentido, fila, col, tamaño, pasos);

        String parametros = "color=" + color
                + ", forma=" + forma
                + ", sentido=" + sentido
                + ", fila=" + fila
                + ", col=" + col
                + ", tamaño=" + tamaño
                + ", pasos=" + pasos;

        String resultado;
        if (ok) {
            resultado = "Movimiento en grupo valido. El tablero fue modificado.";
        } else {
            resultado = "Movimiento en grupo invalido. El tablero no fue modificado.";
        }

        return new String[]{parametros, resultado};
    }

    private String[] ejecutarCaso4() {
        String parametros = "(sin parametros)";
        String resultado = "Tablero preparado correctamente. Ver matriz resultante.";

        return new String[]{parametros, resultado};
    }

    private String[] ejecutarCaso5(Scanner in) {
        char color = pedirColor(in);
        boolean conectado = this.tablero.verificarConexion(this.tablero.getMatriz(), color);

        String parametros = "color=" + color;
        String resultado;
        if (conectado) {
            resultado = "Las fichas " + color + " estan conectadas.";
        } else {
            resultado = "Las fichas " + color + " NO estan conectadas.";
        
            }
        return new String[]{parametros, resultado};
    }
        

    
    /*OPCION D - CONSULTA DE TESTERS*/

    public void consultaTesters(Scanner in) {
        if (this.listaTesters.isEmpty()) {
            System.out.println("No hay testers registrados.");
        } else {
            Tester testerElegido = elegirTester(in);
            ArrayList<Testeo> testeos = testerElegido.getTesteosOrdenados();

            if (testeos.isEmpty()) {
                System.out.println("El tester elegido no tiene testeos registrados.");
            } else {
                System.out.println("Testeos realizados por " + testerElegido.getNombre() + ":");

                for (int i = 0; i < testeos.size(); i = i + 1) {
                    Testeo actual = testeos.get(i);
                    System.out.println((i + 1) + ") Testeo numero "
                            + actual.getNumero() + " - Caso " + actual.getCaso());
                }

                int opcion = pedirEnteroEnRango(in, "Elija un testeo: ", 1, testeos.size());
                Testeo testeoElegido = testeos.get(opcion - 1);

                mostrarDetalleTesteo(testeoElegido);
            }
        }
    }

    /* OPCION E - ESTADISTICAS*/

    //Primera pasada: Busca el maximo de testeos realizados
    //Segunda pasada: Se listan todos los testers que igualan ese maximo (permite empate) 
    public void mostrarEstadisticas() {
        if (this.listaTesters.isEmpty()) {
            System.out.println("No hay testers registrados.");
        } else {
            int maximo = 0;

            for (int i = 0; i < this.listaTesters.size(); i = i + 1) {
                int cantidad = this.listaTesters.get(i).getCantidadTesteos();

                if (cantidad > maximo) {
                    maximo = cantidad;
                }
            }

            System.out.println();
            System.out.println("------------------------------");

            if (maximo == 0) {
                System.out.println("Ningun tester realizo testeos todavia.");
            } else {
                System.out.println("Tester(s) con mayor cantidad de testeos (" + maximo + "):");

                for (int i = 0; i < this.listaTesters.size(); i = i + 1) {
                    Tester actual = this.listaTesters.get(i);

                    if (actual.getCantidadTesteos() == maximo) {
                        System.out.println("- " + actual);
                    }
                }
            }

            System.out.println();
            System.out.println("Tester(s) sin testeos:");

            boolean haySinTesteos = false;

            for (int i = 0; i < this.listaTesters.size(); i = i + 1) {
                Tester actual = this.listaTesters.get(i);

                if (actual.getCantidadTesteos() == 0) {
                    System.out.println("- " + actual);
                    haySinTesteos = true;
                }
            }

            if (!haySinTesteos) {
                System.out.println("(No hay testers sin testeos)");
            }

            System.out.println("------------------------------");
        }
    }
}
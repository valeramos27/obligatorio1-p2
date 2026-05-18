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
    private int proximoNumeroTesteo;


    public Sistema() {
        this.listaTesters = new ArrayList<>();
        this.tablero = new Tablero();
        this.tablero.cargarPorDefecto();
        this.proximoNumeroTesteo = 1;
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
    System.out.println("Comentario: " + testeo.comentario());
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
        // Opcion 3
            // 1. Validar que haya testers
            if (this.listaTesters.isEmpty()) {
                System.out.println("No hay testers registrados. Registre al menos uno antes de testear.");
                return;
            }

            // 2. Mostrar lista de testers y elegir
            ArrayList<Tester> testersOrdenados = this.obtenerTestersOrdenadosPorNombre();
            System.out.println("Lista de testers:");
            for (int i = 0; i < testersOrdenados.size(); i = i + 1) {
                System.out.println((i + 1) + ") " + testersOrdenados.get(i).getNombre());
            }
            System.out.print("Elija un tester: ");
            int opcionTester = in.nextInt();
            while (opcionTester < 1 || opcionTester > testersOrdenados.size()) {
                System.out.print("Opcion invalida. Elija un tester valido: ");
                opcionTester = in.nextInt();
            }
            Tester testerElegido = testersOrdenados.get(opcionTester - 1);

            // 3. Elegir caso
            System.out.println("Casos disponibles:");
            System.out.println("  1) Contar fichas de un color determinado");
            System.out.println("  2) Validar movimiento individual");
            System.out.println("  3) Validar movimiento en grupo");
            System.out.println("  4) Preparar tablero");
            System.out.println("  5) Verificar conexion");
            System.out.print("Ingrese el caso a testear (1-5): ");
            int caso = in.nextInt();
            while (caso < 1 || caso > 5) {
                System.out.print("Caso invalido. Ingrese un numero entre 1 y 5: ");
                caso = in.nextInt();
            }

            // 4. Pedir comentario opcional (limpiar buffer primero)
            in.nextLine();
            System.out.print("Ingrese un comentario sobre de que se va a tratar la prueba (opcional, ENTER para omitir): ");
            String comentario = in.nextLine();
            if (comentario.trim().isEmpty()) {
                comentario = "(sin comentario)";
            }

            // 5. Clonar matriz ANTES de ejecutar
            char[][] matrizOriginal = this.tablero.clonarMatriz(this.tablero.getMatriz());

            // 6. Ejecutar el caso correspondiente
            String parametros = "";
            String resultado = "";

            switch (caso) {
                case 1:
                    String[] datos1 = this.ejecutarCaso1(in);
                    parametros = datos1[0];
                    resultado = datos1[1];
                    break;
                case 2:
                    String[] datos2 = this.ejecutarCaso2(in);
                    parametros = datos2[0];
                    resultado = datos2[1];
                    break;
                case 3:
                    String[] datos3 = this.ejecutarCaso3(in);
                    parametros = datos3[0];
                    resultado = datos3[1];
                    break;
                case 4:
                    String[] datos4 = this.ejecutarCaso4();
                    parametros = datos4[0];
                    resultado = datos4[1];
                    break;
                case 5:
                    String[] datos5 = this.ejecutarCaso5(in);
                    parametros = datos5[0];
                    resultado = datos5[1];
                    break;
            }

            // 7. Clonar matriz resultante
            char[][] matrizResultante = this.tablero.clonarMatriz(this.tablero.getMatriz());

            // 8. Mostrar resultado y matriz por consola
            System.out.println();
            System.out.println("Resultado: " + resultado);
            System.out.println("Matriz actual:");
            this.tablero.mostrar();

            // 9. Crear el Testeo con numero autonumerico y guardarlo
            Testeo nuevo = new Testeo(
                this.proximoNumeroTesteo,
                testerElegido.getNombre(),
                caso,
                parametros,
                comentario,
                resultado,
                matrizOriginal,
                matrizResultante
            );
            testerElegido.agregarTesteo(nuevo);
            this.proximoNumeroTesteo = this.proximoNumeroTesteo + 1;

            System.out.println("Testeo numero " + nuevo.getNumero() + " registrado correctamente.");
        }

        // ============================================================
        // SUBMETODOS PARA CADA CASO
        // ============================================================

        // Caso 1: contarFichas
        private String[] ejecutarCaso1(Scanner in) {
            char color = this.pedirColor(in);
            int cantidad = this.tablero.contarFichas(this.tablero.getMatriz(), color);
            String parametros = "color=" + color;
            String resultado = "Cantidad de fichas " + color + ": " + cantidad;
            return new String[] { parametros, resultado };
        }

        // Caso 2: validarMovimientoIndividual
        private String[] ejecutarCaso2(Scanner in) {
            char color = this.pedirColor(in);
            String sentido = this.pedirSentido(in, true);
            int fila = this.pedirEntero(in, "Ingrese la fila de la ficha a mover (0-7): ", 0, 7);
            int col = this.pedirEntero(in, "Ingrese la columna de la ficha a mover (0-9): ", 0, 9);
            int pasos = this.pedirEntero(in, "Ingrese la cantidad de pasos a desplazar: ", 1, 10);

            boolean ok = this.tablero.moverFicha(fila, col, sentido, color, pasos);

            String parametros = "color=" + color + ", sentido=" + sentido
                    + ", fila=" + fila + ", col=" + col + ", pasos=" + pasos;
            String resultado = ok ? "true" : "false";
            return new String[] { parametros, resultado };
        }

        // Caso 3: validarMovimientoEnGrupo
        private String[] ejecutarCaso3(Scanner in) {
            char color = this.pedirColor(in);
            String forma = this.pedirForma(in);
            String sentido = this.pedirSentido(in, false);

            // Mensaje explicativo segun la forma elegida
            if (forma.equals("H")) {
                System.out.println("(El grupo es horizontal: indique la posicion de la ficha mas al OESTE)");
            } else {
                System.out.println("(El grupo es vertical: indique la posicion de la ficha mas al NORTE)");
            }

            int fila = this.pedirEntero(in, "Ingrese la fila de la ficha extremo (0-7): ", 0, 7);
            int col = this.pedirEntero(in, "Ingrese la columna de la ficha extremo (0-9): ", 0, 9);
            int tam = this.pedirEntero(in, "Ingrese la cantidad de fichas del grupo: ", 1, 10);
            int pasos = this.pedirEntero(in, "Ingrese la cantidad de pasos a desplazar: ", 1, 10);

            boolean ok = this.tablero.moverGrupo(fila, col, tam, forma, sentido, color, pasos);

            String parametros = "color=" + color + ", forma=" + forma + ", sentido=" + sentido
                    + ", fila=" + fila + ", col=" + col + ", tam=" + tam + ", pasos=" + pasos;
            String resultado = ok ? "true" : "false";
            return new String[] { parametros, resultado };
        }

        // Caso 4: prepararTablero
        private String[] ejecutarCaso4() {
            String tableroString = this.tablero.prepararTablero(this.tablero.getMatriz());
            String parametros = "(sin parametros)";
            String resultado = "\n" + tableroString;
            return new String[] { parametros, resultado };
        }

        // Caso 5: verificarConexion
        private String[] ejecutarCaso5(Scanner in) {
            char color = this.pedirColor(in);
            boolean conectadas = this.tablero.verificarConexion(color);
            String parametros = "color=" + color;
            String resultado = conectadas ? "true" : "false";
            return new String[] { parametros, resultado };
        }

        // ============================================================
        // AUXILIARES DE INGRESO Y VALIDACION
        // ============================================================

        private char pedirColor(Scanner in) {
            System.out.print("Ingrese el color (B/N): ");
            String entrada = in.next().toUpperCase();
            while (!entrada.equals("B") && !entrada.equals("N")) {
                System.out.print("Color invalido. Ingrese B o N: ");
                entrada = in.next().toUpperCase();
            }
            return entrada.charAt(0);
        }

        private String pedirSentido(Scanner in, boolean permiteDiagonales) {
            String mensaje = permiteDiagonales
                    ? "Ingrese el sentido (N, S, E, O, NE, NO, SE, SO): "
                    : "Ingrese el sentido (N, S, E, O): ";
            System.out.print(mensaje);
            String entrada = in.next().toUpperCase();

            boolean valido = false;
            while (!valido) {
                if (permiteDiagonales) {
                    valido = entrada.equals("N") || entrada.equals("S") || entrada.equals("E") || entrada.equals("O")
                          || entrada.equals("NE") || entrada.equals("NO") || entrada.equals("SE") || entrada.equals("SO");
                } else {
                    valido = entrada.equals("N") || entrada.equals("S") || entrada.equals("E") || entrada.equals("O");
                }
                if (!valido) {
                    System.out.print("Sentido invalido. " + mensaje);
                    entrada = in.next().toUpperCase();
                }
            }
            return entrada;
        }

        private String pedirForma(Scanner in) {
            System.out.print("Ingrese la forma del grupo (H: fichas en fila / V: fichas en columna): ");
            String entrada = in.next().toUpperCase();
            while (!entrada.equals("H") && !entrada.equals("V")) {
                System.out.print("Forma invalida. Ingrese H (fila horizontal) o V (columna vertical): ");
                entrada = in.next().toUpperCase();
            }
            return entrada;
}

        private int pedirEntero(Scanner in, String mensaje, int min, int max) {
            System.out.print(mensaje);
            int valor = 0;
            boolean valido = false;

            while (!valido) {
        String entrada = in.next();

        // 1. Validar que todos los caracteres sean digitos
        boolean esNumero = true;
        if (entrada.length() == 0) {
            esNumero = false;
        } else {
            int i = 0;
            while (i < entrada.length() && esNumero) {
                char c = entrada.charAt(i);
                if (c < '0' || c > '9') {
                    esNumero = false;
                }
                i = i + 1;
            }
        }

        if (!esNumero) {
            System.out.print("Entrada invalida (\"" + entrada + "\"). Debe ser un numero entre " + min + " y " + max + ". " + mensaje);
        } else {
            // 2. Convertir a entero manualmente
            valor = 0;
            int i = 0;
            while (i < entrada.length()) {
                int digito = entrada.charAt(i) - '0';
                valor = valor * 10 + digito;
                i = i + 1;
            }

            // 3. Validar rango
            if (valor >= min && valor <= max) {
                valido = true;
            } else {
                System.out.print("Valor invalido. Debe estar entre " + min + " y " + max + ". " + mensaje);
            }
        }
    }

    return valor;
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
            // 1. Validar que haya testers
            if (this.listaTesters.isEmpty()) {
                System.out.println("No hay testers registrados.");
                return;
            }

            // 2. Calcular el maximo de testeos realizados
            int maximo = 0;
            for (int i = 0; i < this.listaTesters.size(); i = i + 1) {
                int cantidad = this.listaTesters.get(i).getListaTesteos().size();
                if (cantidad > maximo) {
                    maximo = cantidad;
                }
            }

            // 3. Mostrar testers con mayor cantidad de testeos
            System.out.println();
            System.out.println("------------------------------");
            if (maximo == 0) {
                System.out.println("Ningun tester realizo testeos todavia.");
            } else {
                System.out.println("Tester(s) con mayor cantidad de testeos (" + maximo + "):");
                for (int i = 0; i < this.listaTesters.size(); i = i + 1) {
                    Tester actual = this.listaTesters.get(i);
                    if (actual.getListaTesteos().size() == maximo) {
                        System.out.println("- " + actual);
                    }
                }
            }

            // 4. Mostrar testers sin testeos
            System.out.println();
            System.out.println("Tester(s) sin testeos:");
            boolean hayAlguno = false;
            for (int i = 0; i < this.listaTesters.size(); i = i + 1) {
                Tester actual = this.listaTesters.get(i);
                if (actual.getListaTesteos().isEmpty()) {
                    System.out.println("- " + actual);
                    hayAlguno = true;
                }
            }
            if (!hayAlguno) {
                System.out.println("(no hay)");
            }
            System.out.println("------------------------------");
        }
    }
/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package obligatorio1;

public class Tablero {

    private char[][] tablero;

    public static final char BLANCA = 'B';
    public static final char NEGRA = 'N';
    public static final char VACIO = 'V';

    private static final int FILAS = 8;
    private static final int COLUMNAS = 10;

    public Tablero() {
        tablero = new char[FILAS][COLUMNAS];
        cargarPorDefecto();
    }



    private boolean posicionValida(int fila, int columna) {
        boolean valida = true;

        if (fila < 0 || fila >= FILAS || columna < 0 || columna >= COLUMNAS) {
            valida = false;
        }

        return valida;
    }

    private boolean colorValido(char color) { //Me ayuda a ver si el color es valido para el juego
        boolean valido = false;

        if (color == BLANCA || color == NEGRA) {
            valido = true;
        }

        return valido;
    }

    private boolean celdaValida(char celda) {
        boolean valida = false;

        if (celda == BLANCA || celda == NEGRA || celda == VACIO) {
            valida = true;
        }

        return valida;
    }

    
    private char normalizarColor(char color) {  //Para que el programa acepta mayusculas o minusculas 
        char resultado = Character.toUpperCase(color);
        return resultado;
    }

    private String normalizarTexto(String texto) { 
        String resultado = "";

        if (texto != null) {
            resultado = texto.toUpperCase();
        }

        return resultado;
    }

    private boolean matrizConFormatoValido(char[][] matriz) { //Verifica si la matriz tiene el tamaño correcto
        boolean valida = true;
        //Controlo que la matriz exista y ademas que tenga 8 filas 
        if (matriz == null || matriz.length != FILAS) {
            valida = false;
        } else {
            int fila = 0;

            while (fila < FILAS && valida) {//las recorro 
                if (matriz[fila] == null || matriz[fila].length != COLUMNAS) {//Verifico que la fila exista y que tenga 10 columnas
                    valida = false;
                }

                fila = fila + 1;
            }
        }

        return valida;
    }

    private boolean matrizConCeldasValidas(char[][] matriz) {
        boolean valida = matrizConFormatoValido(matriz);

        int fila = 0;
        while (fila < FILAS && valida) {//Mientras que no se hayan recorrido todas las filas y aun sean validas 
            int columna = 0;

            while (columna < COLUMNAS && valida) {//Mientras que no se hayan recorrido todas las columnas y aun sean validas 
                if (!celdaValida(matriz[fila][columna])) {//Y si la actual no es valida 
                    valida = false;
                }

                columna = columna + 1;
            }

            fila = fila + 1;
        }

        return valida;
    }

    private boolean filaValida(String fila) {
        boolean valida = true;
        String texto = normalizarTexto(fila);

        if (texto.length() != COLUMNAS) {
            valida = false;
        } else {
            int columna = 0;

            while (columna < COLUMNAS && valida) {
                if (!celdaValida(texto.charAt(columna))) {
                    valida = false;
                }

                columna = columna + 1;
            }
        }

        return valida;
    }

    private boolean direccionValidaParaFicha(String sentido, char color) {
        boolean valida = false;

        if (color == BLANCA) {
            if (sentido.equals("N") || sentido.equals("NE") || sentido.equals("NO")
                    || sentido.equals("E") || sentido.equals("O")) {
                valida = true;
            }
        } else {
            if (color == NEGRA) {
                if (sentido.equals("S") || sentido.equals("SE") || sentido.equals("SO")
                        || sentido.equals("E") || sentido.equals("O")) {
                    valida = true;
                }
            }
        }

        return valida;
    }

    private boolean direccionValidaParaGrupo(String sentido, char color) {//No permite diagonales
        boolean valida = false;

        if (color == BLANCA) {
            if (sentido.equals("N") || sentido.equals("E") || sentido.equals("O")) {
                valida = true;
            }
        } else {
            if (color == NEGRA) {
                if (sentido.equals("S") || sentido.equals("E") || sentido.equals("O")) {
                    valida = true;
                }
            }
        }

        return valida;
    }

    private boolean formaValida(String forma) {
        boolean valida = false;

        if (forma.equals("H") || forma.equals("V")) { //Pregunto si es horizontal o vertical
            valida = true;
        }

        return valida;
    }

    private boolean formaCompatibleConDireccion(String forma, String sentido) {
        boolean compatible = false;

        if (forma.equals("H")) {//Si el grupoo esta en forma horizontal
            if (sentido.equals("N") || sentido.equals("S")) {
                compatible = true;
            }
        } else { //Un grupo vertical solo puede moverse horizontalmente
            if (forma.equals("V")) {
                if (sentido.equals("E") || sentido.equals("O")) {
                    compatible = true;
                }
            }
        }

        return compatible;
    }

    private int desplazamientoFila(String sentido) {
        int desplazamiento = 0;

        switch (sentido) {
            case "N":
            case "NE":
            case "NO":
                desplazamiento = -1; //si va hacia el norte la fila disminuye
                break;
            case "S":
            case "SE":
            case "SO":
                desplazamiento = 1; //si va hacia el sur la fila aumenta 
                break;
            default:
                desplazamiento = 0; //en las otras opciones no cambia
                break;
        }

        return desplazamiento;
    }

    private int desplazamientoColumna(String sentido) {
        int desplazamiento = 0;

        switch (sentido) {
            case "E":
            case "NE":
            case "SE":
                desplazamiento = 1;//si va hacia el este la columna aumenta
                break;
            case "O":
            case "NO":
            case "SO":
                desplazamiento = -1;//si va hacia el oeste la columna disminuye 
                break;
            default:
                desplazamiento = 0;
                break;
        }

        return desplazamiento;
    }

    /* CARGA Y CONSULTA DEL TABLERO*/
   

    public void cargarPorDefecto() {
        String[] filas = {
            "VVNNVVNNVV",
            "NNNNNNNNNN",
            "NNVVNNVVNN",
            "VVVVVVVVVV",
            "VVVVVVVVVV",
            "BBVVBBVVBB",
            "BBBBBBBBBB",
            "VVBBVVBBVV"
        };

        cargarMatriz(filas);
    }

    public boolean cargarMatriz(String[] filas) { //Recibe un array de tecto que representa una fila del tablero 
        boolean cargo = true;
 
        if (filas == null || filas.length != FILAS) {//Controlo que el array exista y tenga 8 filas
            cargo = false;
        } else {
            int fila = 0;

            while (fila < FILAS && cargo) {
                if (!filaValida(filas[fila])) {
                    cargo = false;
                }

                fila = fila + 1;
            }
        }

        if (cargo) {//Si todas las filas son validas cargo la matriz
            for (int fila = 0; fila < FILAS; fila = fila + 1) { //recorro filas
                String texto = normalizarTexto(filas[fila]);

                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    tablero[fila][columna] = texto.charAt(columna); //guardo cada caracter en la matriz 
                }
            }
        }

        return cargo;
    }

    public boolean cargarMatriz(char[][] matriz) {//Recibo directamente una matriz ya con caracteres 
        boolean cargo = matrizConCeldasValidas(matriz);//Controlo que tenga formato y celdas validas

        if (cargo) {
            for (int fila = 0; fila < FILAS; fila = fila + 1) {
                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    tablero[fila][columna] = normalizarColor(matriz[fila][columna]);//guardo cada celda 
                }
            }
        }

        return cargo;
    }

    public char[][] getMatriz() {
        char[][] copia = clonarMatriz(tablero);
        return copia;
    }

    public char[][] clonarMatriz(char[][] origen) {
        char[][] copia = new char[FILAS][COLUMNAS];

        if (matrizConFormatoValido(origen)) {
            for (int fila = 0; fila < FILAS; fila = fila + 1) {
                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    copia[fila][columna] = origen[fila][columna];
                }
            }
        } else {
            for (int fila = 0; fila < FILAS; fila = fila + 1) {
                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    copia[fila][columna] = VACIO;
                }
            }
        }

        return copia;
    }

    
    /* CASO 1 - CONTAR FICHAS
    Recorre toda la matriz y cuenta cuantas fichas hay de un color (B o N).*/
    

    public int contarFichas(char[][] matriz, char color) {
        color = normalizarColor(color); //Por ejemplo si viene b, queda B

        int contador = 0;

        if (colorValido(color) && matrizConFormatoValido(matriz)) {
            for (int fila = 0; fila < FILAS; fila = fila + 1) {
                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    if (matriz[fila][columna] == color) {//Pregunto si esa celda tiene el color que busco
                        contador = contador + 1;
                    }
                }
            }
        }

        return contador;
    }

    /* CASO 2 - MOVIMIENTO INDIVIDUAL
    Verifica como una ficha puede moverse*/
   

    private boolean caminoLibreFicha(int fila, int columna, int df, int dc, int pasos, char color) {
        //Me ayuda a ver si una ficha puede recorrer ese camino 
        boolean libre = true;
        int paso = 1;

        while (paso <= pasos && libre) {
            //calculo la posicion a revisar
            int nuevaFila = fila + df * paso;
            int nuevaColumna = columna + dc * paso;
           
            if (!posicionValida(nuevaFila, nuevaColumna)) {//si esta fuera del tablero
                libre = false;
            } else {
                if (paso < pasos) {//si no es el ultimo paso, reviso el camino intermedio
                    if (tablero[nuevaFila][nuevaColumna] != VACIO) {
                        libre = false;//si hay uan ficha en el medio, no puede pasar
                    }
                } else {
                    if (tablero[nuevaFila][nuevaColumna] == color) {
                        libre = false;
                    }
                }
            }

            paso = paso + 1;
        }

        return libre;
    }

    public boolean validarMovimientoIndividual(char color, String sentido, int fila,
            int columna, int pasos) {

        boolean valido = true;

        color = normalizarColor(color);
        sentido = normalizarTexto(sentido);

        if (!posicionValida(fila, columna)) {
            valido = false;
        }

        if (valido && !colorValido(color)) {
            valido = false;
        }

        if (valido && pasos <= 0) {// Si al menos hay un paso 
            valido = false;
        }

        if (valido && tablero[fila][columna] != color) { //Que en la posicion inicial haya una ficha del color indicado
            valido = false;
        }

        if (valido && !direccionValidaParaFicha(sentido, color)) {
            valido = false;
        }

        int df = 0;
        int dc = 0;
        int filaFinal = fila;
        int columnaFinal = columna;

        if (valido) {
            df = desplazamientoFila(sentido);
            dc = desplazamientoColumna(sentido);
            
            //Calculo la posicion final 
            filaFinal = fila + df * pasos;
            columnaFinal = columna + dc * pasos;
           
            if (!posicionValida(filaFinal, columnaFinal)) {
                valido = false;
            }
        }

        if (valido && !caminoLibreFicha(fila, columna, df, dc, pasos, color)) {
            valido = false;
        }

        if (valido) {
            tablero[filaFinal][columnaFinal] = color;
            tablero[fila][columna] = VACIO;
        }

        return valido;
    }

    public boolean moverFicha(int fila, int columna, String sentido, char color, int pasos) {
        boolean pudoMover = validarMovimientoIndividual(color, sentido, fila, columna, pasos);
        return pudoMover;
    }

    
    /* CASO 3 - MOVIMIENTO EN GRUPO
    Verifica si un grupo de fichas alineadas puede moverse junto.*/
    

    private boolean esParteDelGrupo(int fila, int columna, int[] filasOrigen,
            int[] columnasOrigen, int tamano) {

        boolean esParte = false;
        int i = 0;

        while (i < tamano && !esParte) {
            if (filasOrigen[i] == fila && columnasOrigen[i] == columna) {//pregunto si esta ficha tiene la misma fila y columna 
                esParte = true;
            }

            i = i + 1;
        }

        return esParte;
    }

    private boolean cargarDatosGrupo(int fila, int columna, int tamano, String forma,
            char color, int df, int dc, int pasos, int[] filasOrigen,
            int[] columnasOrigen, int[] filasFinal, int[] columnasFinal) {

        boolean valido = true;
        int i = 0;

        while (i < tamano && valido) { //Recorro todas las fichas del grupo
            int filaActual = fila;
            int columnaActual = columna;

            if (forma.equals("H")) {
                columnaActual = columna + i; 
            } else {
                filaActual = fila + i;
            }

            if (!posicionValida(filaActual, columnaActual)) {
                valido = false;
            } else {
                if (tablero[filaActual][columnaActual] != color) { 
                    valido = false;
                }
            }

            if (valido) {
                //Calculo donde terminaria esa ficha
                int filaDestino = filaActual + df * pasos;
                int columnaDestino = columnaActual + dc * pasos;

                if (!posicionValida(filaDestino, columnaDestino)) {//si el destino esta dentro del tablero
                    valido = false;
                } else {
                    //guardo la fila y columna original
                    filasOrigen[i] = filaActual;
                    columnasOrigen[i] = columnaActual;
                    //guardo la fila y columna destino
                    filasFinal[i] = filaDestino;
                    columnasFinal[i] = columnaDestino;
                }
            }

            i = i + 1;
        }

        return valido;
    }

    private boolean caminoLibreGrupo(int[] filasOrigen, int[] columnasOrigen,
            int tamano, int df, int dc, int pasos) {

        boolean libre = true;
        int i = 0;

        while (i < tamano && libre) {
            int paso = 1;

            while (paso <= pasos && libre) { 
                int nuevaFila = filasOrigen[i] + df * paso;
                int nuevaColumna = columnasOrigen[i] + dc * paso;

                if (!posicionValida(nuevaFila, nuevaColumna)) {
                    libre = false;
                } else {//Chequeo si esa posicion no pertenece a un grupo ya que pueden pisarse
                    if (!esParteDelGrupo(nuevaFila, nuevaColumna, filasOrigen,
                            columnasOrigen, tamano)
                            && tablero[nuevaFila][nuevaColumna] != VACIO) {
                        libre = false;
                    }
                }

                paso = paso + 1;
            }

            i = i + 1;
        }

        return libre;
    }

    private void aplicarMovimientoGrupo(int[] filasOrigen, int[] columnasOrigen,
            int[] filasFinal, int[] columnasFinal, int tamano, char color) {
        
        //Borro todas las posiciones originales
        for (int i = 0; i < tamano; i = i + 1) {
            tablero[filasOrigen[i]][columnasOrigen[i]] = VACIO;
        }
        //Coloco las nuevas 
        for (int i = 0; i < tamano; i = i + 1) {
            tablero[filasFinal[i]][columnasFinal[i]] = color;
        }
    }

    public boolean validarMovimientoEnGrupo(char color, String forma, String sentido,
            int fila, int columna, int tamano, int pasos) {

        boolean valido = true;

        color = normalizarColor(color);
        forma = normalizarTexto(forma);
        sentido = normalizarTexto(sentido);

        if (!posicionValida(fila, columna)) {
            valido = false;
        }

        if (valido && !colorValido(color)) {
            valido = false;
        }

        if (valido && (tamano <= 0 || pasos <= 0)) {
            valido = false;
        }

        if (valido && !formaValida(forma)) {
            valido = false;
        }

        if (valido && !formaCompatibleConDireccion(forma, sentido)) {
            valido = false;
        }

        if (valido && !direccionValidaParaGrupo(sentido, color)) {
            valido = false;
        }

        int df = 0;
        int dc = 0;

        //Guardo posiciones
        int[] filasOrigen = new int[0];
        int[] columnasOrigen = new int[0];
        int[] filasFinal = new int[0];
        int[] columnasFinal = new int[0];

        if (valido) {
            df = desplazamientoFila(sentido);
            dc = desplazamientoColumna(sentido);

            filasOrigen = new int[tamano];
            columnasOrigen = new int[tamano];
            filasFinal = new int[tamano];
            columnasFinal = new int[tamano];

            valido = cargarDatosGrupo(fila, columna, tamano, forma, color, df, dc,
                    pasos, filasOrigen, columnasOrigen, filasFinal, columnasFinal);
        }

        if (valido && !caminoLibreGrupo(filasOrigen, columnasOrigen, tamano, df, dc, pasos)) {
            valido = false;
        }

        if (valido) {
            aplicarMovimientoGrupo(filasOrigen, columnasOrigen, filasFinal,
                    columnasFinal, tamano, color);
        }

        return valido;
    }

    public boolean moverGrupo(int fila, int columna, int tamano, String forma,
            String sentido, char color, int pasos) {

        boolean pudoMover = validarMovimientoEnGrupo(color, forma, sentido, fila,
                columna, tamano, pasos);
        return pudoMover;
    }

  
    /* CASO 4 - PREPARAR TABLERO*/

    public String prepararTablero(char[][] matriz) {
        String resultado = "";

        if (!matrizConFormatoValido(matriz)) {
            resultado = "Matriz invalida\n";
        } else {
            resultado = resultado + "   ";

            for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                resultado = resultado + "  " + columna + " ";
            }

            resultado = resultado + "\n";

            for (int fila = 0; fila < FILAS; fila = fila + 1) {
                resultado = resultado + "   ";

                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    resultado = resultado + "+---";
                }

                resultado = resultado + "+\n";
                resultado = resultado + " " + fila + " ";

                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    char celda = matriz[fila][columna];

                    if (celda == VACIO) {
                        resultado = resultado + "|   ";
                    } else {
                        resultado = resultado + "| " + celda + " ";
                    }
                }

                resultado = resultado + "|\n";
            }

            resultado = resultado + "   ";

            for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                resultado = resultado + "+---";
            }

            resultado = resultado + "+\n";
        }

        return resultado;
    }

    
    /*CASO 5 - VERIFICAR CONEXIÓN
    Verifica si todas las fichas de un color están conectadas entre sí.*/
    

    public boolean verificarConexion(char color) {
        boolean conectadas = verificarConexion(tablero, color);
        return conectadas;
    }

    public boolean verificarConexion(char[][] matriz, char color) {
        color = normalizarColor(color);

        boolean conectadas = false;
        boolean[][] visitado = new boolean[FILAS][COLUMNAS]; //Creo una matriz para marcar que posiciones ya reviso

        int totalColor = 0;
        //Suponiendo que no encontre ninguna ficha:
        int filaInicio = -1;
        int columnaInicio = -1;

        if (colorValido(color) && matrizConFormatoValido(matriz)) {
            for (int fila = 0; fila < FILAS; fila = fila + 1) {
                for (int columna = 0; columna < COLUMNAS; columna = columna + 1) {
                    if (matriz[fila][columna] == color) {
                        totalColor = totalColor + 1;

                        if (filaInicio == -1) {
                            filaInicio = fila;
                            columnaInicio = columna;
                        }
                    }
                }
            }

            if (totalColor > 0) {
                int cantidadVisitadas = contarConectadas(matriz, filaInicio,
                                        columnaInicio, color, visitado);
                                        /*cuantas fichas del mismo color estan conectadas
                                        desde la primera ficha*/

                if (cantidadVisitadas == totalColor) {
                    conectadas = true;
                }
            }
        }

        return conectadas;
    }

    private int contarConectadas(char[][] matriz, int filaInicio, int columnaInicio,
                                 char color, boolean[][] visitado) {

        int cantidad = 0;

        int[] filasPendientes = new int[FILAS * COLUMNAS];
        int[] columnasPendientes = new int[FILAS * COLUMNAS];

        int inicio = 0;
        int fin = 0;
        
        //Agrego la posicion inicial a revisar 
        filasPendientes[fin] = filaInicio;
        columnasPendientes[fin] = columnaInicio;
        fin = fin + 1;

        while (inicio < fin) {
            int fila = filasPendientes[inicio];
            int columna = columnasPendientes[inicio];
            inicio = inicio + 1;
            
               if (posicionValida(fila, columna)) {
                if (!visitado[fila][columna]) {
                    if (matriz[fila][columna] == color) {

                        visitado[fila][columna] = true;
                        cantidad = cantidad + 1;

                        for (int df = -1; df <= 1; df = df + 1) {//Recorro las posiciones vecinas 
                            for (int dc = -1; dc <= 1; dc = dc + 1) {
                                if (!(df == 0 && dc == 0)) {//Evito que se agregue a si misma                                      
                                    filasPendientes[fin] = fila + df;
                                    columnasPendientes[fin] = columna + dc;
                                    fin = fin + 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        return cantidad;
    }
}

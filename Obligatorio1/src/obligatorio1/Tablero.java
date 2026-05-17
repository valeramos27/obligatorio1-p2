/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package obligatorio1;
import java.util.Scanner;

public class Tablero {

//ARMAR EL TABLERO VACIO     
    private char[][] tablero;
    public static final char BLANCA = 'B';
    public static final char NEGRA = 'N';
    public static final char VACIO = 'V';
    
    //Constructor
    public Tablero() {
        tablero = new char[8][10];
        inicializar();
    }

    private void inicializar() {
        for (int fila = 0; fila < 8; fila = fila + 1) {
            for (int columna = 0; columna < 10; columna = columna + 1) {
                tablero[fila][columna] = VACIO;
            }
    }

    }

    public void mostrar() {
    // Encabezado con números de columna
    System.out.print("   ");  // espacio para alinear con el número de fila
    for (int col = 0; col < 10; col = col + 1) {
        System.out.print("  " + col + " ");
    }
    System.out.println();

    for (int fila = 0; fila < 8; fila = fila + 1) {
        // línea de separación
        System.out.print("   ");  // espacio para la columna de números de fila
        for (int col = 0; col < 10; col = col + 1) {
            System.out.print("+---");
        }
        System.out.println("+");

        // contenido (con número de fila a la izquierda)
        System.out.print(" " + fila + " ");
        for (int col = 0; col < 10; col = col + 1) {
            char celda = tablero[fila][col];
            if (celda == VACIO) {
                System.out.print("|   ");
            } else {
                System.out.print("| " + celda + " ");
            }
        }
        System.out.println("|");
    }

    // última línea
    System.out.print("   ");
    for (int col = 0; col < 10; col = col + 1) {
        System.out.print("+---");
    }
    System.out.println("+");
}
    
    //CARGA DE VALORES PARA EL TABLERO POR DEFECTO 
    public void cargarPorDefecto() {
    for (int fila = 0; fila < 8; fila = fila + 1) {
        for (int columna = 0; columna < 10; columna = columna + 1) {

            if (fila == 0) {
                // patron N (empieza vacio)
                if ((columna / 2) % 2 == 1) {
                    tablero[fila][columna] = NEGRA;
                    } else {
                        tablero[fila][columna] = VACIO;
                }
            } else {
                if (fila == 1) {
                    // todo N
                    tablero[fila][columna] = NEGRA;
                } else {
                        if (fila == 2) {
                            // patrón N (empieza con N)
                            if ((columna / 2) % 2 == 1) {
                                tablero[fila][columna] = VACIO;
                            } else {
                                        tablero[fila][columna] = NEGRA;
                              }
                        } else {
                            if (fila == 5) {
                                // patron B (empieza con B)
                                if ((columna / 2) % 2 == 1) {
                                    tablero[fila][columna] = VACIO;
                                } else {
                                        tablero[fila][columna] = BLANCA;
                                  }
                            } else {
                                if (fila == 6) {
                                    // todo B
                                    tablero[fila][columna] = BLANCA;
                                } else {
                                    if (fila == 7) {
                                        // patron B (empieza vacío)
                                        if ((columna / 2) % 2 == 1) {
                                            tablero[fila][columna] = BLANCA;
                                        } else {
                                                tablero[fila][columna] = VACIO;
                                           }
                                    } else {
                                        // vacio
                                        tablero[fila][columna] = VACIO;
                                    }
                                }
                            }
                        }
                    }
              }

        }
    }
}

    public void cargarManual(Scanner in) {
    for (int fila = 0; fila < 8; fila = fila + 1) {
        System.out.print("Ingrese la fila " + fila + ": ");
        String entrada = in.next().toUpperCase();

        boolean valida = false;
        while (!valida) {
            if (entrada.length() != 10) {
                System.out.print("Error. La fila debe tener exactamente 10 caracteres. Ingrese la fila " + fila + ": ");
                entrada = in.next().toUpperCase();
            } else {
                // Validar que todos los caracteres sean B, N o V
                boolean todosValidos = true;
                int i = 0;
                while (i < 10 && todosValidos) {
                    char c = entrada.charAt(i);
                    if (c != 'B' && c != 'N' && c != 'V') {
                        todosValidos = false;
                    }
                    i = i + 1;
                }

                if (!todosValidos) {
                    System.out.print("Error. Solo se permiten caracteres B, N o V. Ingrese la fila " + fila + ": ");
                    entrada = in.next().toUpperCase();
                } else {
                    valida = true;
                }
            }
        }

        // Cargar la fila en la matriz
        for (int columna = 0; columna < 10; columna = columna + 1) {
            tablero[fila][columna] = entrada.charAt(columna);
        }
    }
}
    public boolean moverFicha(int fila, int col, String sentido, char color, int pasos) {
        boolean pudoMover = true;

        if (fila < 0 || fila >= 8 || col < 0 || col >= 10) {
            pudoMover = false;
        } else {
            if (tablero[fila][col] != color) {
                pudoMover = false;
            }
        }

        if (pudoMover) {
            if (color == 'B') {
                if (!(sentido.equals("N") || sentido.equals("NE") || sentido.equals("NO")
                        || sentido.equals("E") || sentido.equals("O"))) {
                    pudoMover = false;
                }
            } else {
                if (!(sentido.equals("S") || sentido.equals("SE") || sentido.equals("SO")
                        || sentido.equals("E") || sentido.equals("O"))) {
                    pudoMover = false;
                }
            }
        }

        int df = 0;
        int dc = 0;

        if (pudoMover) {
            switch (sentido) {
                case "N":
                    df = -1;
                    dc = 0;
                    break;
                case "S":
                    df = 1;
                    dc = 0;
                    break;
                case "E":
                    df = 0;
                    dc = 1;
                    break;
                case "O":
                    df = 0;
                    dc = -1;
                    break;
                case "NE":
                    df = -1;
                    dc = 1;
                    break;
                case "NO":
                    df = -1;
                    dc = -1;
                    break;
                case "SE":
                    df = 1;
                    dc = 1;
                    break;
                case "SO":
                    df = 1;
                    dc = -1;
                    break;
                default:
                    pudoMover = false;
                    break;
            }
        }

        int filaActual = fila;
        int colActual = col;
        int i = 1;

        while (i <= pasos && pudoMover) {
            filaActual = filaActual + df;
            colActual = colActual + dc;

            if (filaActual < 0 || filaActual >= 8 || colActual < 0 || colActual >= 10) {
                pudoMover = false;
            } else {
                if (i < pasos) {
                    if (tablero[filaActual][colActual] != VACIO) {
                        pudoMover = false;
                    }
                }
            }

            i = i + 1;
        }

        if (pudoMover) {
            if (tablero[filaActual][colActual] == color) {
                pudoMover = false;
            }
        }

        if (pudoMover) {
            tablero[filaActual][colActual] = color;
            tablero[fila][col] = VACIO;
        }

        return pudoMover;
    }
    public boolean moverGrupo(int fila, int col, int tam, String forma, String sentido, char color, int pasos) {
        boolean esValido = true;

        if (fila < 0 || fila >= 8 || col < 0 || col >= 10) {
            esValido = false;
        }

        if (esValido) {
            if (tam <= 0 || pasos <= 0) {
                esValido = false;
            }
        }

        if (esValido) {
            if (forma.equals("H")) {
                if (!(sentido.equals("N") || sentido.equals("S"))) {
                    esValido = false;
                }
            } else {
                if (forma.equals("V")) {
                    if (!(sentido.equals("E") || sentido.equals("O"))) {
                        esValido = false;
                    }
                } else {
                    esValido = false;
                }
            }
        }

        if (esValido) {
            if (color == 'B') {
                if (!(sentido.equals("N") || sentido.equals("E") || sentido.equals("O"))) {
                    esValido = false;
                }
            } else {
                if (color == 'N') {
                    if (!(sentido.equals("S") || sentido.equals("E") || sentido.equals("O"))) {
                        esValido = false;
                    }
                } else {
                    esValido = false;
                }
            }
        }

        int df = 0;
        int dc = 0;

        if (esValido) {
            switch (sentido) {
                case "N":
                    df = -1;
                    dc = 0;
                    break;
                case "S":
                    df = 1;
                    dc = 0;
                    break;
                case "E":
                    df = 0;
                    dc = 1;
                    break;
                case "O":
                    df = 0;
                    dc = -1;
                    break;
                default:
                    esValido = false;
                    break;
            }
        }

        int[] filasOrigen = new int[0];
        int[] colsOrigen = new int[0];
        int[] filasFinal = new int[0];
        int[] colsFinal = new int[0];

        if (esValido) {
          filasOrigen = new int[tam];
          colsOrigen = new int[tam];
          filasFinal = new int[tam];
          colsFinal = new int[tam];
        }

        int i = 0;
        while (i < tam && esValido) {
            int f = fila;
            int c = col;

            if (forma.equals("H")) {
                c = col + i;
            } else {
                f = fila + i;
            }

            if (f < 0 || f >= 8 || c < 0 || c >= 10) {
                esValido = false;
            } else {
                if (tablero[f][c] != color) {
                    esValido = false;
                }
            }

            if (esValido) {
                filasOrigen[i] = f;
                colsOrigen[i] = c;

                int fFinal = f + df * pasos;
                int cFinal = c + dc * pasos;

                if (fFinal < 0 || fFinal >= 8 || cFinal < 0 || cFinal >= 10) {
                    esValido = false;
                } else {
                    filasFinal[i] = fFinal;
                    colsFinal[i] = cFinal;
                }
            }

            i = i + 1;
        }

        i = 0;
        while (i < tam && esValido) {
            int f = filasOrigen[i];
            int c = colsOrigen[i];
            int p = 1;

            while (p <= pasos && esValido) {
                int nuevaF = f + df * p;
                int nuevaC = c + dc * p;
                boolean esParteDelGrupo = false;

                int j = 0;
                while (j < tam && !esParteDelGrupo) {
                    if (filasOrigen[j] == nuevaF && colsOrigen[j] == nuevaC) {
                        esParteDelGrupo = true;
                    }
                    j = j + 1;
                }

                if (!esParteDelGrupo && tablero[nuevaF][nuevaC] != VACIO) {
                    esValido = false;
                }

                p = p + 1;
            }

            i = i + 1;
        }

        if (esValido) {
            for (i = 0; i < tam; i = i + 1) {
                tablero[filasOrigen[i]][colsOrigen[i]] = VACIO;
            }

            for (i = 0; i < tam; i = i + 1) {
                tablero[filasFinal[i]][colsFinal[i]] = color;
            }
        }

        return esValido;
    }
    
    public boolean verificarConexion(char color) {

        boolean[][] visitado = new boolean[8][10];

        int totalColor = 0;
        int filaInicio = -1;
        int colInicio = -1;
        boolean estanConectadas = false;

        for (int fila = 0; fila < 8; fila = fila + 1) {
            for (int col = 0; col < 10; col = col + 1) {

                if (tablero[fila][col] == color) {
                    totalColor = totalColor + 1;

                    if (filaInicio == -1) {
                        filaInicio = fila;
                        colInicio = col;
                    }
                }
            }
        }

        if (totalColor > 0) {
            int cantidadVisitadas = contarConectadas(filaInicio, colInicio, color, visitado);

            if (cantidadVisitadas == totalColor) {
                estanConectadas = true;
            }
        }

        return estanConectadas;
    }
    private int contarConectadas(int fila, int col, char color, boolean[][] visitado) {

        int cantidad = 0;

        if (fila < 0 || fila >= 8 || col < 0 || col >= 10) {
            cantidad = 0;
        } else {
            if (visitado[fila][col]) {
                cantidad = 0;
            } else {
                if (tablero[fila][col] != color) {
                    cantidad = 0;
                } else {
                    visitado[fila][col] = true;
                    cantidad = 1;

                    for (int df = -1; df <= 1; df = df + 1) {
                        for (int dc = -1; dc <= 1; dc = dc + 1) {
                            if (!(df == 0 && dc == 0)) {
                                cantidad = cantidad + contarConectadas(fila + df, col + dc, color, visitado);
                            }
                        }
                    }
                }
            }
        }

        return cantidad;
        }
        
    public int contarFichas(char[][] matriz, char color){
        int contador = 0;
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 10; columna++) {
                if(matriz[fila][columna] == color){
                    contador = contador + 1;
                }                           
            }            
        }
        return contador;
    }
    
    public String prepararTablero(char[][] matriz) {
    String resultado = "";

    // Encabezado con números de columna
    resultado += "   ";
    for (int col = 0; col < 10; col = col + 1) {
        resultado += "  " + col + " ";
    }
    resultado += "\n";

    for (int fila = 0; fila < 8; fila = fila + 1) {
        // línea de separación
        resultado += "   ";
        for (int col = 0; col < 10; col = col + 1) {
            resultado += "+---";
        }
        resultado += "+\n";

        // contenido (con número de fila a la izquierda)
        resultado += " " + fila + " ";
        for (int col = 0; col < 10; col = col + 1) {
            char celda = matriz[fila][col];
            if (celda == VACIO) {
                resultado += "|   ";
            } else {
                resultado += "| " + celda + " ";
            }
        }
        resultado += "|\n";
    }

    // última línea
    resultado += "   ";
    for (int col = 0; col < 10; col = col + 1) {
        resultado += "+---";
    }
    resultado += "+\n";

    return resultado;
}
    public char[][] clonarMatriz(char[][] origen) {
    char[][] copia = new char[8][10];
    for (int fila = 0; fila < 8; fila = fila + 1) {
        for (int columna = 0; columna < 10; columna = columna + 1) {
            copia[fila][columna] = origen[fila][columna];
        }
    }
    return copia;
}
    
    public char[][] getMatriz() {
       return tablero;
}

}
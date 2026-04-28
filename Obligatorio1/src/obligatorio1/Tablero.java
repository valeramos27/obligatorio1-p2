/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package obligatorio1;
import java.util.Scanner;

public class Tablero {

//ARMAR EL TABLERO VACIO     
    private char[][] tablero;

    //Constructor
    public Tablero() {
        tablero = new char[8][10];
        inicializar();
    }

    private void inicializar() {
        for (int fila = 0; fila < 8; fila = fila + 1) {
            for (int columna = 0; columna < 10; columna = columna + 1) {
                tablero[fila][columna] = ' ';
            }
    }

    }

    public void mostrar() {
    for (int fila = 0; fila < 8; fila = fila + 1) {

        // línea de separación
        for (int col = 0; col < 10; col = col + 1) {
            System.out.print("+---");
        }
        System.out.println("+");

        // contenido
        for (int col = 0; col < 10; col = col + 1) {
            System.out.print("| " + tablero[fila][col] + " ");
        }
        System.out.println("|");
    }

    // última línea
    for (int col = 0; col < 10; col = col + 1) {
        System.out.print("+---");
    }
    System.out.println("+");
  }
    
    //CARGA DE VALORES PARA EL TABLERO POR DEFECTO 
    public void cargarPorDefecto() {
    // cargar patrón por defecto
    for (int fila = 0; fila < 8; fila = fila + 1) {
    for (int columna = 0; columna < 10; columna = columna + 1) {

        if (fila == 0) {
            // patrón N (empieza vacío)
            if ((columna / 2) % 2 == 1) {
            tablero[fila][columna] = 'N';
                } else {
                    tablero[fila][columna] = ' ';
            }
        } else {
            if (fila == 1) {
                // todo N
                tablero[fila][columna] = 'N';
                } else {
                    if (fila == 2) {
                        // patrón N (empieza con N)
                        if ((columna / 2) % 2 == 1) {
                            tablero[fila][columna] = ' ';
                                } else {
                                    tablero[fila][columna] = 'N';
                            }
                    } else {
                        if (fila == 5) {
                            // patrón B (empieza con B)
                            if ((columna / 2) % 2 == 1) {
                            tablero[fila][columna] = ' ';
                                } else {
                                    tablero[fila][columna] = 'B';
                            }
                        } else {
                            if (fila == 6) {
                                // todo B
                                tablero[fila][columna] = 'B';
                            } else {
                                if (fila == 7) {
                                    // patrón B (empieza vacío)
                                    if ((columna / 2) % 2 == 1) {
                                    tablero[fila][columna] = 'N';
                                        } else {
                                            tablero[fila][columna] = ' ';
                                    }
                                } else {
                                    // vacío
                                    tablero[fila][columna] = ' ';
                                }
                            }
                        }
                    }
                }
}

    }
}
}

    //CARGA DE VALORES PARA EL TABLERO CARGADO MANUALMENTE
    public void cargarManual(Scanner in) {

    for (int fila = 0; fila < 8; fila = fila + 1) {
        System.out.println("Ingrese la fila " + fila + ":");

        for (int columna = 0; columna < 10; columna = columna + 1) {
            String entrada = in.next();

            while (entrada.length() != 1 || 
                  (entrada.charAt(0) != 'N' && entrada.charAt(0) != 'B' && entrada.charAt(0) != 'V')) {

                System.out.println("Error. Ingrese solo un caracter: N, B o V");
                entrada = in.next();
            }
            char valor = entrada.charAt(0);

            if (valor == 'V') {
                tablero[fila][columna] = ' ';
            } else {
                tablero[fila][columna] = valor;
            }
        }
    }
    }
}
       

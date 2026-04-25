package obligatorio1;

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
        } else {
                    if (fila == 2) {
                        // patrón N (empieza con N)
                    } else {
                        if (fila == 5) {
                            // patrón B (empieza con B)
                        } else {
                            if (fila == 6) {
                                // todo B
                            } else {
                                if (fila == 7) {
                                    // patrón B (empieza vacío)
                                } else {
                                    // vacío
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
    public void cargarManual() {
        // cargar con datos del usuario
    }

}
       

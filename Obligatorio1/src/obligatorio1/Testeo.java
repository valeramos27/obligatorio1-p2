/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package obligatorio1;

public class Testeo implements Comparable<Testeo> {

    private int numero;
    private String nombreTester;
    private int caso;
    private String parametrosUsados;
    private String comentario;
    private String resultado;
    private char[][] matrizOriginal;
    private char[][] matrizResultante;

    public Testeo(int numero, String nombreTester, int caso, String parametrosUsados,
            String comentario, String resultado, char[][] matrizOriginal,
            char[][] matrizResultante) {

        this.numero = numero;
        this.nombreTester = nombreTester;
        this.caso = caso;
        this.parametrosUsados = parametrosUsados;
        this.comentario = comentario;
        this.resultado = resultado;
        this.matrizOriginal = copiarMatriz(matrizOriginal);
        this.matrizResultante = copiarMatriz(matrizResultante);
    }
    private char[][] copiarMatriz(char[][] matriz) {
        char[][] copia = new char[matriz.length][matriz[0].length];

        for (int fila = 0; fila < matriz.length; fila = fila + 1) {
            for (int columna = 0; columna < matriz[0].length; columna = columna + 1) {
                copia[fila][columna] = matriz[fila][columna];
            }
        }

        return copia;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombreTester() {
        return nombreTester;
    }

    public int getCaso() {
        return caso;
    }

    public String getParametrosUsados() {
        return parametrosUsados;
    }

    public String getComentario() {
        return comentario;
    }

    public String getResultado() {
        return resultado;
    }

    public char[][] getMatrizOriginal() {
        char[][] copia = copiarMatriz(matrizOriginal);
        return copia;
    }

    public char[][] getMatrizResultante() {
        char[][] copia = copiarMatriz(matrizResultante);
        return copia;
    }

    @Override
    public int compareTo(Testeo otro) {
        return this.numero - otro.numero;
    }
}
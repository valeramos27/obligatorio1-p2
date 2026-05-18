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
        char[][] copia;

        if (matriz == null) {
            copia = new char[0][0];
        } else {
            copia = new char[matriz.length][];

            for (int fila = 0; fila < matriz.length; fila = fila + 1) {
                if (matriz[fila] == null) {
                    copia[fila] = new char[0];
                } else {
                    copia[fila] = new char[matriz[fila].length];

                    for (int columna = 0; columna < matriz[fila].length; columna = columna + 1) {
                        copia[fila][columna] = matriz[fila][columna];
                    }
                }
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

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNombreTester(String nombreTester) {
        this.nombreTester = nombreTester;
    }

    public void setCaso(int caso) {
        this.caso = caso;
    }

    public void setParametrosUsados(String parametrosUsados) {
        this.parametrosUsados = parametrosUsados;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setMatrizOriginal(char[][] matrizOriginal) {
        this.matrizOriginal = copiarMatriz(matrizOriginal);
    }

    public void setMatrizResultante(char[][] matrizResultante) {
        this.matrizResultante = copiarMatriz(matrizResultante);
    }

    @Override
    public int compareTo(Testeo otro) {
        return this.numero - otro.numero;
    }
}
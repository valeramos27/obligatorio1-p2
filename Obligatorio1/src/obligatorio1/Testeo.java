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

    public Testeo(int numero, String nombreTester, int caso, String parametrosUsados, String comentario, String resultado, char[][] matrizOriginal, char[][] matrizResultante) {
        this.numero = numero;
        this.nombreTester = nombreTester;
        this.caso = caso;
        this.parametrosUsados = parametrosUsados;
        this.comentario = comentario;
        this.resultado = resultado;
        this.matrizOriginal = matrizOriginal;
        this.matrizResultante = matrizResultante;
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
        return matrizOriginal;
    }
    public char[][] getMatrizResultante() {
        return matrizResultante;
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
        this.matrizOriginal = matrizOriginal;
    }
    public void setMatrizResultante(char[][] matrizResultante) {
        this.matrizResultante = matrizResultante;
    }
    
    @Override
    public int compareTo(Testeo otro) {
        return this.numero - otro.numero;
    }
}
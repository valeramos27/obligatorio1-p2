/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package obligatorio1;


public class Testeo {

    private int numero;
    private String nombreTester;
    private int caso;
    private String resultado;

    public Testeo(int numero, String nombreTester, int caso, String resultado) {
        this.numero = numero;
        this.nombreTester = nombreTester;
        this.caso = caso;
        this.resultado = resultado;
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

    public String getResultado() {
        return resultado;
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

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
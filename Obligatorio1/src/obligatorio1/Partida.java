/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package obligatorio1;


public class Partida {

    private Tablero tablero;

    public Partida() {
        this.tablero = new Tablero();
    }

    public Partida(Tablero unTablero) {
        if (unTablero != null) {
            this.tablero = unTablero;
        } else {
            this.tablero = new Tablero();
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public String toString() {
        return "Partida con tablero";
    }
}
/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */
package obligatorio1;
import java.util.ArrayList;

public class Sistema {

    private ArrayList<Partida> listaPartidas;

    public Sistema() {
        listaPartidas = new ArrayList<Partida>();
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void agregarPartida(Partida unaPartida) {
        if (unaPartida != null) {
            this.getListaPartidas().add(unaPartida);
        }
    }

    public int cantidadPartidas() {
        int cantidad = this.getListaPartidas().size();
        return cantidad;
    }
}
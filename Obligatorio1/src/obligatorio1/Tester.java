/*
 Aitana Alvarez - 340201
 Valentina Ramos - 224347
 */

package obligatorio1;
import java.util.ArrayList;

public class Tester {

    private String nombre;
    private int edad;
    private int experiencia;
    private ArrayList<Testeo> listaTesteos;

    public Tester(String nombre, int edad, int experiencia) {
        this.nombre = nombre;
        this.edad = edad;
        this.experiencia = experiencia;
        this.listaTesteos = new ArrayList<>();
    }


    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public ArrayList<Testeo> getListaTesteos() {
        return listaTesteos;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public void setListaTesteos(ArrayList<Testeo> listaTesteos) {
        this.listaTesteos = listaTesteos;
    }
}
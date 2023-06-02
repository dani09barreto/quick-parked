package puj.quickparked.quickparkedmobile.model;

public class SugerenciaParqueadero {
    private String nombre;
    private double distancia;

    public SugerenciaParqueadero(String nombre, double distancia) {
        this.nombre = nombre;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
}

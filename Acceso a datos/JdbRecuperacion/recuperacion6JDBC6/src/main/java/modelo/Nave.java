package modelo;
import java.util.ArrayList;
import java.util.List;

public class Nave {
    private int id;
    private String nombre;
    private String modelo;
    private List<Tripulante> tripulacion; // Relación 1:N

    public Nave() { this.tripulacion = new ArrayList<>(); }
    public Nave(int id, String nombre, String modelo) {
        this.id = id;
        this.nombre = nombre;
        this.modelo = modelo;
        this.tripulacion = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public List<Tripulante> getTripulacion() { return tripulacion; }
    public void setTripulacion(List<Tripulante> tripulacion) { this.tripulacion = tripulacion; }

    @Override
    public String toString() {
        return "NAVE: " + nombre + " [" + modelo + "] | Tripulantes: " + tripulacion.size();
    }
}
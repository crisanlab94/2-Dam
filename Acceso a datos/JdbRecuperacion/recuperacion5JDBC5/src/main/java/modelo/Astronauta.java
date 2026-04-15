package modelo;

public class Astronauta {
    private int id;
    private String nombre;
    private Seguridad seguridad; // Relación 1:1

    public Astronauta() {}

    public Astronauta(int id, String nombre, Seguridad seguridad) {
        this.id = id;
        this.nombre = nombre;
        this.seguridad = seguridad;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Seguridad getSeguridad() { return seguridad; }
    public void setSeguridad(Seguridad seguridad) { this.seguridad = seguridad; }

    @Override
    public String toString() {
        return "Astronauta: " + nombre + " (ID: " + id + ") | Credenciales: " + seguridad;
    }
}
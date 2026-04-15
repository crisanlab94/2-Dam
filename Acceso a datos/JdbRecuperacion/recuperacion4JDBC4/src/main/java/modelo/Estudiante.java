package modelo;

public class Estudiante {
    private int id;
    private String nombre;
    private Nivel nivel;

    public Estudiante() {}

    public Estudiante(int id, String nombre, Nivel nivel) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }

    @Override
    public String toString() {
        return "Estudiante [ID=" + id + ", Nombre=" + nombre + ", Nivel=" + nivel + "]";
    }
}

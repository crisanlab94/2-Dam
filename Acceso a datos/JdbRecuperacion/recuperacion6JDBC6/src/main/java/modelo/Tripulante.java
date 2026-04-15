package modelo;

public class Tripulante {
    private int id;
    private String nombre;
    private String rango;
    private boolean activo;
    private int idNave; // FK

    public Tripulante() {}
    public Tripulante(int id, String nombre, String rango, boolean activo, int idNave) {
        this.id = id;
        this.nombre = nombre;
        this.rango = rango;
        this.activo = activo;
        this.idNave = idNave;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRango() { return rango; }
    public void setRango(String rango) { this.rango = rango; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public int getIdNave() { return idNave; }
    public void setIdNave(int idNave) { this.idNave = idNave; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - Activo: %b", id, nombre, rango, activo);
    }
}

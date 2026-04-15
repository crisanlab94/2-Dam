package modelo;
import java.time.LocalDate;
import java.util.*;

public class Empresa {
    private int id;
    private String nombre;
    private Sector sector;
    private LocalDate fechaCreacion;
    private boolean esMultinacional;
    private List<Empleado> plantilla = new ArrayList<>();

    public Empresa() {}
    public Empresa(int id, String nombre, Sector sector, LocalDate fecha, boolean multi) {
        this.id = id; this.nombre = nombre; this.sector = sector;
        this.fechaCreacion = fecha; this.esMultinacional = multi;
    }
    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public Sector getSector() { return sector; }
    public void setSector(Sector s) { this.sector = s; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate f) { this.fechaCreacion = f; }
    public boolean isEsMultinacional() { return esMultinacional; }
    public void setEsMultinacional(boolean m) { this.esMultinacional = m; }
    public List<Empleado> getPlantilla() { return plantilla; }
    public void setPlantilla(List<Empleado> p) { this.plantilla = p; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return id == empresa.id;
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nombre=" + nombre + ", sector=" + sector + ", fechaCreacion=" + fechaCreacion
				+ ", esMultinacional=" + esMultinacional + "]";
	}
    
    
}
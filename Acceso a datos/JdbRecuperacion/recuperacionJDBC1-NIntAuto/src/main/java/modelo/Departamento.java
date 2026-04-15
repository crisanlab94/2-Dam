package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Departamento {
    private String id;
    private String nombre;
    private double presupuesto;
    private LocalDate fechaFundacion;
    private boolean esOficial;
    private List<Profesor> profesores = new ArrayList<>();

    public Departamento() {}
    public Departamento(String id, String nombre, double presupuesto, LocalDate fecha, boolean oficial) {
        this.id = id; this.nombre = nombre; this.presupuesto = presupuesto;
        this.fechaFundacion = fecha; this.esOficial = oficial;
    }
    // Getters y Setters...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double p) { this.presupuesto = p; }
    public LocalDate getFechaFundacion() { return fechaFundacion; }
    public void setFechaFundacion(LocalDate f) { this.fechaFundacion = f; }
    public boolean isEsOficial() { return esOficial; }
    public void setEsOficial(boolean o) { this.esOficial = o; }
    public List<Profesor> getProfesores() { return profesores; }
    public void setProfesores(List<Profesor> p) { this.profesores = p; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento d = (Departamento) o;
        return Objects.equals(id, d.id);
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + ", presupuesto=" + presupuesto + ", esOficial="
				+ esOficial + "]";
	}
    
    
}

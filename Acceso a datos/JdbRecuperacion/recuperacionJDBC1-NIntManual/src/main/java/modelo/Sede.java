package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sede {
    private int id;
    private String nombre;
    private LocalDate fechaApertura;
    private boolean esInternacional;
    private List<Sala> listaSalas = new ArrayList<>();

    public Sede() {}
    public Sede(int id, String nom, LocalDate fecha, boolean inter) {
        this.id = id; this.nombre = nom; this.fechaApertura = fecha; this.esInternacional = inter;
    }
    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate f) { this.fechaApertura = f; }
    public boolean isEsInternacional() { return esInternacional; }
    public void setEsInternacional(boolean e) { this.esInternacional = e; }
    public List<Sala> getListaSalas() { return listaSalas; }
    public void setListaSalas(List<Sala> s) { this.listaSalas = s; }
    
    
	@Override
	public String toString() {
		return "Sede [id=" + id + ", nombre=" + nombre + ", esInternacional=" + esInternacional + "]";
	}
    
    
}

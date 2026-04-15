package modelo;

import java.time.LocalDateTime;

public class Drone {
    private int id;
    private String modelo;
    private Categoria categoria;
    private LocalDateTime ultimaMision;
    private int nivelBateria;
    private boolean necesitaReparacion;
    private Componente componente;

    public Drone() {}
    public Drone(int id, String mod, Categoria cat, LocalDateTime fecha, int bat, boolean rep) {
        this.id = id; this.modelo = mod; this.categoria = cat;
        this.ultimaMision = fecha; this.nivelBateria = bat; this.necesitaReparacion = rep;
    }
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getModelo() { return modelo; }
    public void setModelo(String m) { this.modelo = m; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria c) { this.categoria = c; }
    public LocalDateTime getUltimaMision() { return ultimaMision; }
    public void setUltimaMision(LocalDateTime u) { this.ultimaMision = u; }
    public int getNivelBateria() { return nivelBateria; }
    public void setNivelBateria(int n) { this.nivelBateria = n; }
    public boolean isNecesitaReparacion() { return necesitaReparacion; }
    public void setNecesitaReparacion(boolean n) { this.necesitaReparacion = n; }
    public Componente getComponente() { return componente; }
    public void setComponente(Componente c) { this.componente = c; }
    
	@Override
	public String toString() {
		return "Drone [id=" + id + ", modelo=" + modelo + ", categoria=" + categoria + ", ultimaMision=" + ultimaMision
				+ ", nivelBateria=" + nivelBateria + ", necesitaReparacion=" + necesitaReparacion + ", componente="
				+ componente + "]";
	}
    
    
}

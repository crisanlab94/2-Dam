package modelo;

import java.time.LocalDateTime;

public class Consultorio {
    private int planta;
    private double costeMantenimiento;
    private LocalDateTime ultimaInspeccion;

    public Consultorio() {}
    public Consultorio(int planta, double coste, LocalDateTime ultima) {
        this.planta = planta;
        this.costeMantenimiento = coste;
        this.ultimaInspeccion = ultima;
    }
    // Getters y Setters...
    public int getPlanta() { return planta; }
    public void setPlanta(int p) { this.planta = p; }
    public double getCosteMantenimiento() { return costeMantenimiento; }
    public void setCosteMantenimiento(double c) { this.costeMantenimiento = c; }
    public LocalDateTime getUltimaInspeccion() { return ultimaInspeccion; }
    public void setUltimaInspeccion(LocalDateTime u) { this.ultimaInspeccion = u; }
	@Override
	public String toString() {
		return "Consultorio [planta=" + planta + ", costeMantenimiento=" + costeMantenimiento + ", ultimaInspeccion="
				+ ultimaInspeccion + "]";
	}
    
    
}

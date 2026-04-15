package modelo;

import java.time.LocalDate;

public class Medico {
    private int id;
    private String nombre;
    private Especialidad especialidad;
    private String email;
    private LocalDate fechaAlta;
    private boolean estaActivo;
    private Consultorio consultorio;

    public Medico() {}
    public Medico(int id, String nom, Especialidad esp, String email, LocalDate fecha, boolean activo) {
        this.id = id; this.nombre = nom; this.especialidad = esp;
        this.email = email; this.fechaAlta = fecha; this.estaActivo = activo;
    }
    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad e) { this.especialidad = e; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate f) { this.fechaAlta = f; }
    public boolean isEstaActivo() { return estaActivo; }
    public void setEstaActivo(boolean a) { this.estaActivo = a; }
    public Consultorio getConsultorio() { return consultorio; }
    public void setConsultorio(Consultorio c) { this.consultorio = c; }
    
	@Override
	public String toString() {
		return "Medico [id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + ", email=" + email
				+ ", fechaAlta=" + fechaAlta + ", estaActivo=" + estaActivo + ", consultorio=" + consultorio + "]";
	}
    
    
}

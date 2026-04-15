package modelo;

import java.util.Objects;

public class Profesor {
    private String dni;
    private String nombre;
    private Especialidad especialidad;
    private double salario;

    public Profesor() {}
    public Profesor(String dni, String nombre, Especialidad esp, double salario) {
        this.dni = dni; this.nombre = nombre; this.especialidad = esp; this.salario = salario;
    }
    // Getters y Setters...
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profesor p = (Profesor) o;
        return Objects.equals(dni, p.dni);
    }
    @Override
    public int hashCode() { return Objects.hash(dni); }
	@Override
	public String toString() {
		return "Profesor [dni=" + dni + ", nombre=" + nombre + ", especialidad=" + especialidad + ", salario=" + salario
				+ "]";
	}
    
    
}

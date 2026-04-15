package modelo;
import java.util.Objects;

public class Empleado {
    private int id;
    private String nombre;
    private double salario;
    private boolean esRemoto;

    public Empleado() {}
    public Empleado(int id, String nombre, double salario, boolean remoto) {
        this.id = id; this.nombre = nombre; this.salario = salario; this.esRemoto = remoto;
    }
    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public double getSalario() { return salario; }
    public void setSalario(double s) { this.salario = s; }
    public boolean isEsRemoto() { return esRemoto; }
    public void setEsRemoto(boolean r) { this.esRemoto = r; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado e = (Empleado) o;
        return id == e.id;
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", salario=" + salario + ", esRemoto=" + esRemoto + "]";
	}
    
    
}
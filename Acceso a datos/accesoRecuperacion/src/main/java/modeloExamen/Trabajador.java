package modeloExamen;

public class Trabajador {
	private String nombre;
	private String dni;
	private String fechaNacimiento;
	private Tipo tipo;
	private String idCentro;
	

	public Trabajador(String nombre, String dni, String fechaNacimiento, Tipo tipo, String idCentro) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.idCentro = idCentro;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getIdCentro() {
		return idCentro;
	}


	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}


	@Override
	public String toString() {
		return "Trabajador [nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", tipo="
				+ tipo + ", idCentro=" + idCentro + "]";
	}


	
	

}

package mongoDB.Modelo;

public class Asignatura {

	private String nombre;
	private String codigo;
	private String profesor;
	
	
	
	
	public Asignatura() {
		super();
	}


	public Asignatura(String nombre, String codigo, String profesor) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.profesor = profesor;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getProfesor() {
		return profesor;
	}


	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}


	@Override
	public String toString() {
		return "Asignatura [nombre=" + nombre + ", codigo=" + codigo + ", profesor=" + profesor + "]";
	}
	
	
	
	
	
}

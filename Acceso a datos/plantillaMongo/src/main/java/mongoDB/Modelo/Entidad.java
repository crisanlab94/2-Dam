package mongoDB.Modelo;

public class Entidad {
	//Crear los atributos que esten dentro de el documento anidado(en este caso Entidad)
	private Tipo tipo;
	private String nombre;
	private String direccion;
	
	
	
	//Constructor vacio
	public Entidad() {
		super();
	}


	public Entidad(Tipo tipo, String nombre, String direccion) {
		super();
		this.tipo = tipo;
		this.nombre = nombre;
		this.direccion = direccion;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	@Override
	public String toString() {
		return "Entidad [tipo=" + tipo + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}

	
	
	
	

}

package xml.dam.modelo;

public class Empleado {

	int edad;
	String nombreApellido;
	String identificador;
	String empresa;
	
	
	
	public Empleado() {
		super();
	}

	public Empleado(int edad, String nombreApellido, String identificador, String empresa) {
		super();
		this.edad = edad;
		this.nombreApellido = nombreApellido;
		this.identificador = identificador;
		this.empresa = empresa;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return "Empleado [edad=" + edad + ", nombreApellido=" + nombreApellido + ", identificador=" + identificador
				+ ", empresa=" + empresa + "]";
	}
	
	
	
	
}

package lecturaFicheros;

import java.util.ArrayList;
import java.util.List;

public class Persona {
	String nombre;
	List notas;
	
	public Persona(String nombre) {
		super();
		this.nombre = nombre;
		this.notas = new ArrayList<Integer>() ;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List getNotas() {
		return notas;
	}

	public void setNotas(List notas) {
		this.notas = notas;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", notas=" + notas + "]";
	}
	
	
	

}

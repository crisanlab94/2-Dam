package modelo;

import java.util.Objects;

public class Equipo {
	private String nombre;
	private String codigo;
	private int numJugadores;
	private String emailContacto;
	
	public Equipo(String nombre, String codigo, int numJugadores, String emailContacto) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.numJugadores = numJugadores;
		this.emailContacto = emailContacto;
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

	public int getNumJugadores() {
		return numJugadores;
	}

	public void setNumJugadores(int numJugadores) {
		this.numJugadores = numJugadores;
	}

	public String getEmailContacto() {
		return emailContacto;
	}

	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", codigo=" + codigo + ", numJugadores=" + numJugadores + ", emailContacto="
				+ emailContacto + "]";
	}
	
	
	
	
	

}

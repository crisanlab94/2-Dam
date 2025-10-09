package accesoADatos.repaso.libreria.models;

import java.util.Objects;

public class Editorial implements Comparable <Editorial>{

	private String nombre;
	private String direccion;
	private String cif;
	private String web;
	private String emailContacto;
	
	
	public Editorial(String nombre, String direccion, String cif, String web, String emailContacto) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.cif = cif;
		this.web = web;
		this.emailContacto = emailContacto;
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


	public String getCif() {
		return cif;
	}


	public void setCif(String cif) {
		this.cif = cif;
	}


	public String getWeb() {
		return web;
	}


	public void setWeb(String web) {
		this.web = web;
	}


	public String getEmailContacto() {
		return emailContacto;
	}


	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cif, nombre);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Editorial other = (Editorial) obj;
		return Objects.equals(cif, other.cif) && Objects.equals(nombre, other.nombre);
	}


	@Override
	public String toString() {
		return "Editorial [nombre=" + nombre + ", direccion=" + direccion + ", cif=" + cif + ", web=" + web
				+ ", emailContacto=" + emailContacto + "]";
	}


	@Override
	public int compareTo(Editorial editorial) {
		
		return 0;
	}
	
	
}

package modeloExamen;

import java.util.ArrayList;
import java.util.List;

public class Grupo {
	private String idCentro;
	private String nombre;
	private String ciudad;
	private int numComedores;
	private List<Trabajador>listaTrabajadores;
	
	public Grupo(String idCentro, String nombre, String ciudad, int numComedores) {
		super();
		this.idCentro = idCentro;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.numComedores = numComedores;
		this.listaTrabajadores = new ArrayList<Trabajador>();
	}

	public String getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNumComedores() {
		return numComedores;
	}

	public void setNumComedores(int numComedores) {
		this.numComedores = numComedores;
	}

	public List<Trabajador> getListaTrabajadores() {
		return listaTrabajadores;
	}

	public void setListaTrabajadores(List<Trabajador> listaTrabajadores) {
		this.listaTrabajadores = listaTrabajadores;
	}

	@Override
	public String toString() {
		return "CentroLogistico [idCentro=" + idCentro + ", nombre=" + nombre + ", ciudad=" + ciudad + ", numComedores="
				+ numComedores + "]";
	}
	
	
	
	

}

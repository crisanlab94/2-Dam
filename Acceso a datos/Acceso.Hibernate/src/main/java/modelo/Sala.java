package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="sala")
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSala;
	private String nombre;
	private int capacidad;
	
	



	public int getIdSala() {
		return idSala;
	}


	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCapacidad() {
		return capacidad;
	}


	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	//hay que añadir reunion a sala 
	
	public void addReunion(Reunion r)
	{
		this.reuniones.add(r);
		r.setSala(this);
	}


	
	//El One va en el fuerte(el que tenga mucos(n/m) en la realcion)
	@OneToMany (mappedBy= "sala")
	private List<Reunion> reuniones;

	public Sala() {
		super();
		this.reuniones = new ArrayList<Reunion>();
	}

	public Sala(String nombre) {
        this(); 
        this.nombre = nombre;
        this.capacidad = 5;
    }

	public Sala(int idSala, String nombre, int capacidad) {
		super();
		this.idSala = idSala;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.reuniones = new ArrayList<Reunion>();
	}



	@Override
	public String toString() {
		return "Sala [idSala=" + idSala + ", nombre=" + nombre + ", capacidad=" + capacidad + "]";
	}
	
	
	
	
	

}

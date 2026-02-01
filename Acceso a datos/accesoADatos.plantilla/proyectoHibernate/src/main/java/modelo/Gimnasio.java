package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Gimnasio {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    
 // RELACIÓN 1:1 con Dirección
    // CascadeType.ALL para que al guardar el Gym se guarde la Dirección automáticamente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;
    
 // RELACIÓN 1:N UNIDIRECCIONAL con Entrenador
    // Como el Entrenador no sabe nada del Gym, ponemos el JoinColumn aquí
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_gimnasio") 
    private List<Entrenador> entrenadores = new ArrayList<Entrenador>();

    // RELACIÓN 1:N BIDIRECCIONAL con Socio
    // El "mappedBy" debe coincidir con el nombre de la variable en la clase Socio
    @OneToMany(mappedBy = "gimnasio", cascade = CascadeType.ALL)
    private List<Socio> socios = new ArrayList<Socio>();

	public Gimnasio() {
		super();
	}
	
	

	public Gimnasio(String nombre, Direccion direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
	}



	public Gimnasio(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public List<Entrenador> getEntrenadores() {
		return entrenadores;
	}

	public void setEntrenadores(List<Entrenador> entrenadores) {
		this.entrenadores = entrenadores;
	}

	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	@Override
	public String toString() {
		return "Gimnasio [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
    
    

}

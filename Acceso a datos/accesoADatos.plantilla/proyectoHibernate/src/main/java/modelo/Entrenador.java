package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Entrenador {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String especialidad;
    
    @Enumerated(EnumType.STRING)
    private Turno turno; 

    private boolean estaActivo;
    
    @ManyToOne
    @JoinColumn(name = "id_gimnasio") 
    private Gimnasio gimnasio;
    
    
	public Entrenador() {
		super();
	}


	public Entrenador(String nombre, String especialidad) {
		super();
		this.nombre = nombre;
		this.especialidad = especialidad;
	}

	
	

	public Entrenador(String nombre, String especialidad, Turno turno, boolean estaActivo) {
		super();
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.turno = turno;
		this.estaActivo = estaActivo;
	}

	

	public Entrenador(String nombre, String especialidad, Turno turno, boolean estaActivo, Gimnasio gimnasio) {
		super();
		this.nombre = nombre;
		this.especialidad = especialidad;
		this.turno = turno;
		this.estaActivo = estaActivo;
		this.gimnasio = gimnasio;
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


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	
	

	public Turno getTurno() {
		return turno;
	}


	public void setTurno(Turno turno) {
		this.turno = turno;
	}


	public boolean isEstaActivo() {
		return estaActivo;
	}


	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}

	

	public Gimnasio getGimnasio() {
		return gimnasio;
	}


	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}


	@Override
	public String toString() {
		return "Entrenador [id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + ", turno=" + turno
				+ ", estaActivo=" + estaActivo + ", gimnasio=" + gimnasio + "]";
	}



}

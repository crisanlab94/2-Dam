package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;





@Entity
@Table(name="equipo")
public class Equipo {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEquipo;
	@Column(nullable = false)
	private String nombre;
	private int puntosAcumulados;
	
	//Este es el lado 1
	//No es el fuerte por eso lleva mappedBy
	@OneToMany(mappedBy = "equipo", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	 private List<Jugador> jugadores;
	
	//Relacion N:M no manda
	@ManyToMany(mappedBy = "equipos")
	 private List<Fase> fases;

	public Equipo() {
		super();
		this.fases = new ArrayList<Fase>();
	}

	
	


	public Equipo(String nombre, int puntosAcumulados) {
		super();
		this.nombre = nombre;
		this.puntosAcumulados = puntosAcumulados;
		this.jugadores = new ArrayList<Jugador>();
		this.fases = new ArrayList<Fase>();
	}





	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}

	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}



	public List<Jugador> getJugadores() {
		return jugadores;
	}


	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}





	public List<Fase> getFases() {
		return fases;
	}





	public void setFases(List<Fase> fases) {
		this.fases = fases;
	}





	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombre=" + nombre + ", puntosAcumulados=" + puntosAcumulados
				+ ", jugadores=" + jugadores + "]";
	}



	 //1:N
	 public void addJugador(Jugador j) {
		    if (this.jugadores == null) {
		        this.jugadores = new ArrayList<>();
		    }
		 
		    this.jugadores.add(j);
		    
		    // 2. IMPORTANTE: En la relación 1:N, el jugador solo tiene UN equipo.
		    // No es una lista, es un campo simple. 
		    j.setEquipo(this); 
		}
	 

	
	
	
	
	

}

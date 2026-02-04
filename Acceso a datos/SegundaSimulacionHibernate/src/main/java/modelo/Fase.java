package modelo;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="fase")
public class Fase {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFase;
	private String nombreFase;
	private LocalDateTime fecha;

	//Para que sea bidireccional una clase lleva mappeBy y la otra no
	//La fuerte no lleva mappedBy
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Equipo> equipos;

	public Fase() {
		super();
		this.equipos = new ArrayList<Equipo>();
	}

	

	public Fase(String nombreFase, LocalDateTime fecha) {
		super();
		this.nombreFase = nombreFase;
		this.fecha = fecha;
		this.equipos = new ArrayList<Equipo>();
	}



	public int getIdFase() {
		return idFase;
	}

	public void setIdFase(int idFase) {
		this.idFase = idFase;
	}

	public String getNombreFase() {
		return nombreFase;
	}

	public void setNombreFase(String nombreFase) {
		this.nombreFase = nombreFase;
	}

	

	public LocalDateTime getFecha() {
		return fecha;
	}



	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}



	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}


	
	public void addEquipo(Equipo e) {
	    if (this.equipos == null) {
	        this.equipos = new ArrayList<>();
	    }
	    // Añadimos el equipo a la lista de esta fase
	    this.equipos.add(e);
	    
	    // Como es bidireccional, añadimos esta fase a la lista del equipo
	    if (e.getFases() == null) {
	        e.setFases(new ArrayList<>());
	    }
	    e.getFases().add(this);
	}



	@Override
	public String toString() {
		return "Fase [idFase=" + idFase + ", nombreFase=" + nombreFase + ", fecha=" + fecha + "]";
	}
	
	
	
}

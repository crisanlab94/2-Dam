package modelos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "evento")
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEvento;
	
	@Column(nullable = false) // Este atributo no puede ser nulo
	private String nombre;
	private String tipoEvento;
	private LocalDateTime fecha;
	private int duracion;

	@ManyToOne()
	@JoinColumn(name = "idUbicacion", nullable = true)
	private Ubicacion ubicacion;

	@ManyToMany()
	private Set<Participante> participantes;
	
	public Evento() {
		super();
		this.participantes = new HashSet<Participante>();
	}
	
	public Evento(String nombre) {
		super();
		this.nombre = nombre;
		this.participantes = new HashSet<Participante>();
	}

	public Evento(String nombre, String tipoEvento) {
		super();
		this.nombre = nombre;
		this.tipoEvento = tipoEvento;
		this.duracion = 100;
		this.participantes = new HashSet<Participante>();
	}

	public Evento(String nombre, String tipoEvento, LocalDateTime fecha, int duracion) {
		super();
		this.nombre = nombre;
		this.tipoEvento = tipoEvento;
		this.fecha = fecha;
		this.duracion = duracion;
		this.participantes = new HashSet<Participante>();
	}

	public void addAParticipante(Participante a) {
		this.participantes.add(a);
		if (!a.getEventos().contains(this)) 
		{
			a.getEventos().add(this);
		}
	}

	public String getNombre() {
		return nombre;
	} 

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getIdEvento() {
		return idEvento;
	}
	
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public Set<Participante> getParticipantes() {
		return participantes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEvento);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return idEvento == other.idEvento;
	}

	@Override
	public String toString() {
		return "Evento [idEvento=" + idEvento + ", nombre=" + nombre + ", tipoEvento=" + tipoEvento + ", fecha=" + fecha
				+ ", duracion=" + duracion + "]";
	}
}

package modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ubicacion")
public class Ubicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idUbicacion;
	private String nombre;
	private String direccion;
	private int capacidadMaxima;

	@OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
	private Set<Evento> eventos;

	public void addEvento(Evento e) {

		if (!this.eventos.contains(e)) {
			this.eventos.add(e);
		}
		e.setUbicacion(this);
	}

	public Ubicacion() {
		super();
		this.eventos = new HashSet<Evento>();
	}

	public Ubicacion(String nombre, String direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = 30;
		this.eventos = new HashSet<Evento>();
	}

	public Ubicacion(String nombre, String direccion, int capacidadMaxima) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
		this.eventos = new HashSet<Evento>();
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

	public int getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public void setCapacidadMaxima(int capacidadMaxima) {
		this.capacidadMaxima = capacidadMaxima;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUbicacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ubicacion other = (Ubicacion) obj;
		return idUbicacion == other.idUbicacion;
	}

	@Override
	public String toString() {
		return "Ubicacion [idUbicacion=" + idUbicacion + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", capacidadMaxima=" + capacidadMaxima + "]";
	}
}

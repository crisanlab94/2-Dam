package modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "participante")
public class Participante {
	@Id
	private String dni;
	private String nombre;
	private String apellidos;
	private String email;

	@ManyToMany(mappedBy = "participantes", cascade = CascadeType.ALL)
	private Set<Evento> eventos;

	public Participante() {
		super();
		this.eventos = new HashSet<Evento>();
	}

	public Participante(String dni, String nombre, String apellidos, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.eventos = new HashSet<Evento>();
	}

	
	
	public Participante(String nombre, String apellidos, String email) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public void addEvento(Evento evento) {
		this.eventos.add(evento);
		if (!evento.getParticipantes().contains(this)) {
			evento.getParticipantes().add(this);
		}
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	} 

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Evento> getEventos() {
		return eventos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participante other = (Participante) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "Participante [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ "]";
	}

}

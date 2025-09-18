package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {
	private String idUsuario;
	private String nombre;
	private String email;
	private LocalDate fecha;
	private boolean esStreamer;
	
	
	
	public Usuario(String idUsuario, String nombre, String email, LocalDate fecha,boolean esStreamer) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.email = email;
		this.fecha = fecha;
		this.esStreamer = esStreamer;
	}
	
	


	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", email=" + email + ", fecha=" + fecha + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}




	

}

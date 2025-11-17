package modelo;

import java.util.Objects;

public class Enfrentamiento {
	private String id;
	private String fecha;
	private String descripcion;
	private Videojuego videoJuego;
	private String equipoGanador;
	
	
	public Enfrentamiento(String id, String fecha, String descripcion, Videojuego videoJuego,String equipoGanador) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.videoJuego = videoJuego;
		this.equipoGanador = equipoGanador;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Videojuego getVideoJuego() {
		return videoJuego;
	}


	public void setVideoJuego(Videojuego videoJuego) {
		this.videoJuego = videoJuego;
	}




	public String getEquipoGanador() {
		return equipoGanador;
	}


	public void setEquipoGanador(String equipoGanador) {
		this.equipoGanador = equipoGanador;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enfrentamiento other = (Enfrentamiento) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Enfrentamiento [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", videoJuego="
				+ /*videoJuego + */ ", equipoGanador=" + equipoGanador + "]";
	}
	
	

}

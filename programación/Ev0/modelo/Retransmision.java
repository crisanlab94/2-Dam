package modelo;

import java.time.LocalDate;

public class Retransmision {
	private String idRetransmision;
	private String titulo;
	private LocalDate fechaConHora;
	private float  duracion;
	
	
	public Retransmision(String idRetransmision, String titulo, LocalDate fechaConHora, float duracion) {
		super();
		this.idRetransmision = idRetransmision;
		this.titulo = titulo;
		this.fechaConHora = fechaConHora;
		this.duracion = duracion;
	}


	public String getIdRetransmision() {
		return idRetransmision;
	}


	public void setIdRetransmision(String idRetransmision) {
		this.idRetransmision = idRetransmision;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public LocalDate getFechaConHora() {
		return fechaConHora;
	}


	public void setFechaConHora(LocalDate fechaConHora) {
		this.fechaConHora = fechaConHora;
	}


	public float getDuracion() {
		return duracion;
	}


	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}


	@Override
	public String toString() {
		return "Retransmision [idRetransmision=" + idRetransmision + ", titulo=" + titulo + ", fechaConHora="
				+ fechaConHora + ", duracion=" + duracion + "]";
	}
	
	

}

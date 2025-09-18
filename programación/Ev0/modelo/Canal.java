package modelo;

import java.time.LocalDate;

public class Canal {
	private String idCanal;
	private String titulo;
	private LocalDate fechaCreacion;
	
	public Canal(String idCanal, String titulo, LocalDate fechaCreacion) {
		super();
		this.idCanal = idCanal;
		this.titulo = titulo;
		this.fechaCreacion = fechaCreacion;
	}

	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Override
	public String toString() {
		return "Canal [idCanal=" + idCanal + ", titulo=" + titulo + ", fechaCreacion=" + fechaCreacion + "]";
	}
	
	
	
	

}

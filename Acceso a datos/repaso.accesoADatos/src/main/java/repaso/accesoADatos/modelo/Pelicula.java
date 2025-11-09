package repaso.accesoADatos.modelo;

import java.util.List;

public class Pelicula {
	private String titulo;
    private int fecha; // año de la película
    private String director;
    private List<String> actores;
    
    
	public Pelicula() {
		super();
	}
	public Pelicula(String titulo, int fecha, String director, List<String> actores) {
		super();
		this.titulo = titulo;
		this.fecha = fecha;
		this.director = director;
		this.actores = actores;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public List<String> getActores() {
		return actores;
	}
	public void setActores(List<String> actores) {
		this.actores = actores;
	}
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", fecha=" + fecha + ", director=" + director + ", actores=" + actores
				+ "]";
	}
    
    

}

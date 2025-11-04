package modelo.peliculas;



import java.util.ArrayList;

import java.util.List;



public class Pelicula {

	private String titulo;

	private int anyo;

	private String director;

	private List<String> listaActores;

	



	public Pelicula() {

		super();

	}



	public Pelicula(String titulo, int anyo, String director) {

		super();

		this.titulo = titulo;

		this.anyo = anyo;

		this.director = director;

		this.listaActores= new ArrayList<String>();

	}



	public String getTitulo() {

		return titulo;

	}



	public void setTitulo(String titulo) {

		this.titulo = titulo;

	}



	public int getAnyo() {

		return anyo;

	}



	public void setAnyo(int anyo) {

		this.anyo = anyo;

	}



	public String getDirector() {

		return director;

	}



	public void setDirector(String director) {

		this.director = director;

	}



	public List<String> getListaActores() {

		return listaActores;

	}



	public void setListaActores(List<String> listaActores) {

		this.listaActores = listaActores;

	}



	@Override

	public String toString() {

		return "Pelicula [titulo=" + titulo + ", anyo=" + anyo + ", director=" + director + ", listaActores="

				+ listaActores + "]";

	}
}	





	

	

	

	

	
package accesoADatos.repaso.libreria.models;

import java.util.Objects;

public class Libro {
	private String id;
	private String titulo;
	private String autor;
	private int anioPrublicacion;
	private Genero genero;
	private Editorial editorial;
	private int numEjemplares;
	
	public Libro(String id, String titulo, String autor, int anioPrublicacion, Genero genero, Editorial editorial,
			int numEjemplares) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anioPrublicacion = anioPrublicacion;
		this.genero = genero;
		this.editorial = editorial;
		this.numEjemplares = numEjemplares;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getAnioPrublicacion() {
		return anioPrublicacion;
	}

	public void setAnioPrublicacion(int anioPrublicacion) {
		this.anioPrublicacion = anioPrublicacion;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public int getNumEjemplares() {
		return numEjemplares;
	}

	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
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
		Libro other = (Libro) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", anioPrublicacion=" + anioPrublicacion
				+ ", genero=" + genero + ", editorial=" + editorial + ", numEjemplares=" + numEjemplares + "]";
	}
	
	

}

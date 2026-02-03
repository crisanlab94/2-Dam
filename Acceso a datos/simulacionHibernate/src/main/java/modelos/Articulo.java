package modelos;



import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="articulo")
public class Articulo {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArticulo;
	private String titulo;
	private int numPaginaInicio;
	private int numPagFin;
	
	 @ManyToOne
	 @JoinColumn(name="idRevista")
	 private Revista revista;
	 
	 @ManyToMany(cascade = CascadeType.MERGE) 
	 private List<Autor> autores;

	 public Articulo() {
		super();
	 }

	 public Articulo( String titulo, int numPaginaInicio, int numPagFin, Revista revista) {
		super();
	
		this.titulo = titulo;
		this.numPaginaInicio = numPaginaInicio;
		this.numPagFin = numPagFin;
		this.revista = revista;
		this.autores = new ArrayList<Autor>();
	 }
	 
	 
	 

	 public Articulo(String titulo, int numPaginaInicio, int numPagFin) {
		super();
		this.titulo = titulo;
		this.numPaginaInicio = numPaginaInicio;
		this.numPagFin = numPagFin;
	}

	 public int getIdArticulo() {
		 return idArticulo;
	 }

	 public void setIdArticulo(int idArticulo) {
		 this.idArticulo = idArticulo;
	 }

	 public String getTitulo() {
		 return titulo;
	 }

	 public void setTitulo(String titulo) {
		 this.titulo = titulo;
	 }

	 public int getNumPaginaInicio() {
		 return numPaginaInicio;
	 }

	 public void setNumPaginaInicio(int numPaginaInicio) {
		 this.numPaginaInicio = numPaginaInicio;
	 }

	 public int getNumPagFin() {
		 return numPagFin;
	 }

	 public void setNumPagFin(int numPagFin) {
		 this.numPagFin = numPagFin;
	 }

	 public Revista getRevista() {
		 return revista;
	 }

	 public void setRevista(Revista revista) {
		 this.revista = revista;
	 }

	 
	 
	 public List<Autor> getAutores() {
		return autores;
	}

	 public void setAutores(List<Autor> autores) {
		 this.autores = autores;
	 }

	 @Override
	 public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", titulo=" + titulo + ", numPaginaInicio=" + numPaginaInicio
				+ ", numPagFin=" + numPagFin + ", revista=" + revista + "]";
	 }
	
	 
	 public void addAutor(Autor a) {
		    if (this.autores == null) {
		        this.autores = new ArrayList<>();
		    }
		    // Añadimos el autor a la lista del artículo
		    this.autores.add(a);
		    
		    // Como es bidireccional (N:M), también añadimos el artículo al autor
		    if (a.getArticulos() == null) {
		        a.setArticulos(new ArrayList<>());
		    }
		    a.getArticulos().add(this);
		}
	
	

}

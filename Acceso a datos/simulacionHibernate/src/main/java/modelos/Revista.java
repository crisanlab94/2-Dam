package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Revista {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRevista;
	private String nombreRevista;
	private int numeroRevista;
	private LocalDate fecha;
	private int unidadesImpresas;
	
	//El fuerte(N) es One
		 @OneToMany(mappedBy = "revista",cascade = CascadeType.MERGE)
		    private List<Articulo> listaArticulos = new ArrayList<Articulo>();
	
	public Revista() {
		super();
	}


	public Revista(String nombreRevista, int numeroRevista, LocalDate fecha, int unidadesImpresas) {
		super();
		
		this.nombreRevista = nombreRevista;
		this.numeroRevista = numeroRevista;
		this.fecha = fecha;
		this.unidadesImpresas = unidadesImpresas;
		this.listaArticulos = new ArrayList<Articulo>();
	}



	 public int getIdRevista() {
		 return idRevista;
	 }


	 public void setIdRevista(int idRevista) {
		 this.idRevista = idRevista;
	 }


	 public String getNombreRevista() {
		 return nombreRevista;
	 }


	 public void setNombreRevista(String nombreRevista) {
		 this.nombreRevista = nombreRevista;
	 }


	 public int getNumeroRevista() {
		 return numeroRevista;
	 }


	 public void setNumeroRevista(int numeroRevista) {
		 this.numeroRevista = numeroRevista;
	 }


	 public LocalDate getFecha() {
		 return fecha;
	 }


	 public void setFecha(LocalDate fecha) {
		 this.fecha = fecha;
	 }


	 public int getUnidadesImpresas() {
		 return unidadesImpresas;
	 }


	 public void setUnidadesImpresas(int unidadesImpresas) {
		 this.unidadesImpresas = unidadesImpresas;
	 }


	 public List<Articulo> getListaArticulos() {
		 return listaArticulos;
	 }


	 public void setListaArticulos(List<Articulo> listaArticulos) {
		 this.listaArticulos = listaArticulos;
	 }


	 @Override
	 public String toString() {
		return "Revista [idRevista=" + idRevista + ", nombreRevista=" + nombreRevista + ", numeroRevista="
				+ numeroRevista + ", fecha=" + fecha + ", unidadesImpresas=" + unidadesImpresas + "]";
	 }
	 
	 
	 //1:N
	 public void addArticulo(Articulo a) {
		    if (this.listaArticulos == null) {
		        this.listaArticulos = new ArrayList<>();
		    }
		 
		    this.listaArticulos.add(a);
		    
		    // 2. IMPORTANTE: En la relación 1:N, el artículo solo tiene UNA revista.
		    // No es una lista, es un campo simple. Seteamos la revista en el artículo.
		    a.setRevista(this); 
		}
	 
	 

}

package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Socio {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_gimnasio")
    private Gimnasio gimnasio;

    @OneToOne(cascade = CascadeType.ALL)
    private FichaMedica fichaMedica;

  
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Actividad> actividades = new ArrayList<Actividad>();
    
    @Enumerated(EnumType.STRING)
    private TipoSuscripcion suscripcion; 

    private boolean estaAlCorrientePago;

    

	public Socio() {
		super();
	}



	public Socio(String nombre, Gimnasio gimnasio, FichaMedica fichaMedica) {
		super();
		this.nombre = nombre;
		this.gimnasio = gimnasio;
		this.fichaMedica = fichaMedica;
	}
	

	public Socio(String nombre, Gimnasio gimnasio, FichaMedica fichaMedica, TipoSuscripcion suscripcion,
			boolean estaAlCorrientePago) {
		super();
		this.nombre = nombre;
		this.gimnasio = gimnasio;
		this.fichaMedica = fichaMedica;
		this.suscripcion = suscripcion;
		this.estaAlCorrientePago = estaAlCorrientePago;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Gimnasio getGimnasio() {
		return gimnasio;
	}



	public void setGimnasio(Gimnasio gimnasio) {
		this.gimnasio = gimnasio;
	}



	public FichaMedica getFichaMedica() {
		return fichaMedica;
	}



	public void setFichaMedica(FichaMedica fichaMedica) {
		this.fichaMedica = fichaMedica;
	}



	public List<Actividad> getActividades() {
		return actividades;
	}



	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}






	@Override
	public String toString() {
		return "Socio [id=" + id + ", nombre=" + nombre + ", gimnasio=" + gimnasio + ", fichaMedica=" + fichaMedica
				+ ", suscripcion=" + suscripcion + ", estaAlCorrientePago=" + estaAlCorrientePago + "]";
	}



	public TipoSuscripcion getSuscripcion() {
		return suscripcion;
	}



	public void setSuscripcion(TipoSuscripcion suscripcion) {
		this.suscripcion = suscripcion;
	}



	public boolean isEstaAlCorrientePago() {
		return estaAlCorrientePago;
	}



	public void setEstaAlCorrientePago(boolean estaAlCorrientePago) {
		this.estaAlCorrientePago = estaAlCorrientePago;
	}
    
    
}

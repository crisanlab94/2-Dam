package proyectoSpring.models;

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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "socio")
public class Socio {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_gimnasio")
    private Gimnasio gimnasio;


  
    @ManyToMany(cascade = {CascadeType.MERGE})
    private List<Actividad> actividades = new ArrayList<Actividad>();
    
 

    private boolean estaAlCorrientePago;

    

	public Socio() {
		super();
	}



	public Socio(String nombre, Gimnasio gimnasio) {
		super();
		this.nombre = nombre;
		this.gimnasio = gimnasio;
		
	}
	

	public Socio(String nombre, Gimnasio gimnasio,
			boolean estaAlCorrientePago) {
		super();
		this.nombre = nombre;
		this.gimnasio = gimnasio;
		
		this.estaAlCorrientePago = estaAlCorrientePago;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
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



	



	public List<Actividad> getActividades() {
		return actividades;
	}



	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}










	public boolean isEstaAlCorrientePago() {
		return estaAlCorrientePago;
	}



	public void setEstaAlCorrientePago(boolean estaAlCorrientePago) {
		this.estaAlCorrientePago = estaAlCorrientePago;
	}
    
    
}

package proyectoSpring.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "gimnasio")
public class Gimnasio {
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column
    private String nombre;
    private boolean estaAbierto=true;


    // RELACIÓN M:N BIDIRECCIONAL con Socio
    // El "mappedBy" debe coincidir con el nombre de la variable en la clase Socio
    @OneToMany(mappedBy = "gimnasio", cascade = CascadeType.ALL)
    private List<Socio> socios = new ArrayList<Socio>();

	public Gimnasio() {
		super();
	}
	


	public Gimnasio(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	

	public Gimnasio(String nombre, boolean estaAbierto) {
		super();
		this.nombre = nombre;
		this.estaAbierto = estaAbierto;
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

	

	public boolean isEstaAbierto() {
		return estaAbierto;
	}



	public void setEstaAbierto(boolean estaAbierto) {
		this.estaAbierto = estaAbierto;
	}



	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	
    

}

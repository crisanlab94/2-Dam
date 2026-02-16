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

	

	public List<Socio> getSocios() {
		return socios;
	}

	public void setSocios(List<Socio> socios) {
		this.socios = socios;
	}

	
    

}

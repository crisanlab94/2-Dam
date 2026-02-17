package acceso.veterinaria.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;   

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "vacunas")
public class Vacuna 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVacuna;
	private String nombre;
	private String partida;
	private String farmaceutica;
	
	@ManyToMany()
	@JoinTable(name = "animal_vacuna", joinColumns = @JoinColumn(name = "idVacuna"), inverseJoinColumns = @JoinColumn(name = "idAnimal"))
	private List<Animal> animales;

	public Vacuna(String nombre, String partida, String farmaceutica) {
		this.nombre = nombre;
		this.partida = partida;
		this.farmaceutica = farmaceutica;
		this.animales = new ArrayList<Animal>(); 
		}
	
	

	public Vacuna() {
		super();
	}



	public Long getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(Long idVacuna) {
		this.idVacuna = idVacuna;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getFarmaceutica() {
		return farmaceutica;
	}

	public void setFarmaceutica(String farmaceutica) {
		this.farmaceutica = farmaceutica;
	}

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}
    
	
	
    
}

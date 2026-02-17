package acceso.veterinaria.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;

    private String nombre;
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private TipoAnimal tipo;

    // Relación ManyToMany con la clase Vacuna
    @ManyToMany(mappedBy = "animales", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vacuna> vacunas;
    
    public Animal(String nombre, LocalDate fechaNacimiento, TipoAnimal tipo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
        this.vacunas = new ArrayList<Vacuna>(); 
    }
    
    

	public Animal() {
		super();
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public TipoAnimal getTipo() {
		return tipo;
	}

	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}

	public List<Vacuna> getVacunas() {
		return vacunas;
	}

	public void setVacunas(List<Vacuna> vacunas) {
		this.vacunas = vacunas;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
    
    
    
    
}

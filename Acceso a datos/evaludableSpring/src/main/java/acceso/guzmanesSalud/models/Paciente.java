package acceso.guzmanesSalud.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String dni;  

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConstantesVitales> constantesVitales;

	public Paciente(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}

	public Paciente() {
		super();
	}

	public Long getIdPaciente() {
		return id;
	}

	public void setIdPaciente(Long idPaciente) {
		this.id = idPaciente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<ConstantesVitales> getConstantesVitales() {
		return constantesVitales;
	}

	public void setConstantesVitales(List<ConstantesVitales> constantesVitales) {
		this.constantesVitales = constantesVitales;
	}
    
    
	
}

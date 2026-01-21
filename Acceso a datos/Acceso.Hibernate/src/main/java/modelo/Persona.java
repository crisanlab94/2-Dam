package modelo;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "persona")
public class Persona {
	@Id
	
	@Column(name = "dni", nullable = false, length = 15)
	private String dni; 



	@Column(nullable = false, length = 100)

	private String nombreApellido;



	@Column(nullable = true)

	private Integer edad;



	@Column(nullable = true, length = 150, unique = true)

	private String email;



	@Column(nullable = false)

	private LocalDate fechaNacimiento;



	@Column(nullable = true, length = 15)

	private String telefono;

	
	@ManyToMany()
	Set<Reunion> reuniones;
	

	

	public Persona() {

		super();

	}

	



	
	


	public Persona(String dni,String nombreApellido, Integer edad, String email, LocalDate fechaNacimiento,
			String telefono) {
		super();
		this.dni = dni;
		this.nombreApellido = nombreApellido;
		this.edad = edad;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.reuniones = new HashSet <Reunion>();;
	}









	public String getDni() {

		return dni;

	}



	public void setDni(String dni) {

		this.dni = dni;

	}



	public String getNombreApellido() {

		return nombreApellido;

	}



	public void setNombreApellido(String nombreApellido) {

		this.nombreApellido = nombreApellido;

	}



	public Integer getEdad() {

		return edad;

	}



	public void setEdad(Integer edad) {

		this.edad = edad;

	}



	public String getEmail() {

		return email;

	}



	public void setEmail(String email) {

		this.email = email;

	}



	public LocalDate getFechaNacimiento() {

		return fechaNacimiento;

	}



	public void setFechaNacimiento(LocalDate fechaNacimiento) {

		this.fechaNacimiento = fechaNacimiento;

	}



	public String getTelefono() {

		return telefono;

	}



	public void setTelefono(String telefono) {

		this.telefono = telefono;

	}
	
	
	

	public Set<Reunion> getReuniones() {
		return reuniones;
	}

	public void addReunion (Reunion r) {
		this.reuniones.add(r);
		if(!r.getPersonas().contains(this))
		{
			r.getPersonas().add(this);
		}
	}
	
	public void removeReunion (Reunion r) {
		this.reuniones.remove(r);
		if(!r.getPersonas().contains(this))
		{
			r.getPersonas().remove(this);
		}
	}
	
	

}

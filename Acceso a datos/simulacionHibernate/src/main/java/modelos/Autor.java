package modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="autor")
public class Autor {
	@Id 
	private String dni;
	private String nombre;
	private String email;
	
	@ManyToMany(mappedBy = "autores")
	private List<Articulo> articulos;

	public Autor() {
		super();
	}

	public Autor(String dni, String nombre, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.email = email;
		this.articulos = new ArrayList<Articulo>();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "Autor [dni=" + dni + ", nombre=" + nombre + ", email=" + email + "]";
	}
	
	
	

}

package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Direccion {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String calle;
    private String ciudad;
    
    //Importante constructor vacio
	public Direccion() {
		super();
	}

	public Direccion(String calle, String ciudad) {
		super();
		this.calle = calle;
		this.ciudad = ciudad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", ciudad=" + ciudad + "]";
	}
	
	
    
    

}

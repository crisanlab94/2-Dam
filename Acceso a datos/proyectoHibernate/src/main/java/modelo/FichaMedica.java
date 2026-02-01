package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FichaMedica {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double peso;
    private double altura;
    private String observaciones;
    
    
    
	public FichaMedica() {
		super();
	}



	public FichaMedica(double peso, String observaciones) {
		super();
		this.peso = peso;
		this.observaciones = observaciones;
	}



	public FichaMedica(double peso, double altura) {
		super();
		this.peso = peso;
		this.altura = altura;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public double getPeso() {
		return peso;
	}



	public void setPeso(double peso) {
		this.peso = peso;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public double getAltura() {
		return altura;
	}



	public void setAltura(double altura) {
		this.altura = altura;
	}



	@Override
	public String toString() {
		return "FichaMedica [id=" + id + ", peso=" + peso + ", altura=" + altura + ", observaciones=" + observaciones
				+ "]";
	}



	


    
	

}

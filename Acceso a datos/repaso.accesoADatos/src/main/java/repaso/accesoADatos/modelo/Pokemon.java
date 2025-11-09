package repaso.accesoADatos.modelo;

import java.util.List;
import java.util.Objects;

public class Pokemon {
	private int id;
    private String nombre;
    private String tipo;
    private double altura_m;
    private double peso_kg;
    private List<String> habilidades;
    private String evolucionaA;
    
    
	public Pokemon() {
		super();
	}


	public Pokemon(int id, String nombre, String tipo, double altura_m, double peso_kg, List<String> habilidades,
			String evolucionaA) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.altura_m = altura_m;
		this.peso_kg = peso_kg;
		this.habilidades = habilidades;
		this.evolucionaA = evolucionaA;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public double getAltura_m() {
		return altura_m;
	}


	public void setAltura_m(double altura_m) {
		this.altura_m = altura_m;
	}


	public double getPeso_kg() {
		return peso_kg;
	}


	public void setPeso_kg(double peso_kg) {
		this.peso_kg = peso_kg;
	}


	public List<String> getHabilidades() {
		return habilidades;
	}


	public void setHabilidades(List<String> habilidades) {
		this.habilidades = habilidades;
	}


	public String getEvolucionaA() {
		return evolucionaA;
	}


	public void setEvolucionaA(String evolucionaA) {
		this.evolucionaA = evolucionaA;
	}

	
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return id == other.id;
	}


	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", altura_m=" + altura_m + ", peso_kg="
				+ peso_kg + ", habilidades=" + habilidades + ", evolucionaA=" + evolucionaA + "]";
	}
    
    

}

package repaso.accesoADatos.modelo;

import java.util.Arrays;

public class Combinaciones {
	private int [] numeros;
	private int [] estrellas;
	
	
	public Combinaciones(int[] numeros, int[] estrellas) {
		super();
		this.numeros = numeros;
		this.estrellas = estrellas;
	}


	public int[] getNumeros() {
		return numeros;
	}


	public void setNumeros(int[] numeros) {
		this.numeros = numeros;
	}


	public int[] getEstrellas() {
		return estrellas;
	}


	public void setEstrellas(int[] estrellas) {
		this.estrellas = estrellas;
	}


	@Override
	public String toString() {
		return "Combinaciones [numeros=" + Arrays.toString(numeros) + ", estrellas=" + Arrays.toString(estrellas) + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(estrellas);
		result = prime * result + Arrays.hashCode(numeros);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Combinaciones other = (Combinaciones) obj;
		return Arrays.equals(estrellas, other.estrellas) && Arrays.equals(numeros, other.numeros);
	}
	
	

}

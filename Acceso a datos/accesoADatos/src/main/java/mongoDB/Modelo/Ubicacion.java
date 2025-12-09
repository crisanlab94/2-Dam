package mongoDB.Modelo;

public class Ubicacion {
	private String calle;
	private int numero;
	private int codigoPostal;
	private Coordenadas coordenadas;
	
	
	public Ubicacion(String calle, int numero, int codigoPostal, Coordenadas coordenadas) {
		super();
		this.calle = calle;
		this.numero = numero;
		this.codigoPostal = codigoPostal;
		this.coordenadas = coordenadas;
	}


	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getCodigoPostal() {
		return codigoPostal;
	}


	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public Coordenadas getCoordenadas() {
		return coordenadas;
	}


	public void setCoordenadas(Coordenadas coordenadas) {
		this.coordenadas = coordenadas;
	}


	@Override
	public String toString() {
		return "Ubicacion [calle=" + calle + ", numero=" + numero + ", codigoPostal=" + codigoPostal + ", coordenadas="
				+ coordenadas + "]";
	}
	
	

}

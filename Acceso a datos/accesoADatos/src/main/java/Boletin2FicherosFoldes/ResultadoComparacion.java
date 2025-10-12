package Boletin2FicherosFoldes;

public class ResultadoComparacion {
	String nombreArchivo;
	Valor valorArchivo;
	
	
	
	public ResultadoComparacion() {
		super();
	}


	public ResultadoComparacion(String nombreArchivo, Valor valorArchivo) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.valorArchivo = valorArchivo;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public Valor getValorArchivo() {
		return valorArchivo;
	}


	public void setValorArchivo(Valor valorArchivo) {
		this.valorArchivo = valorArchivo;
	}


	@Override
	public String toString() {
		return "Archivo:" + nombreArchivo +"  Estado:" + valorArchivo;
	}
	
	
	
}

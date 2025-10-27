package Boletin2FicherosFoldes;

import Boletin2FicherosFoldesEnum.Valor;

public class ResultadoComparacion {
	private String nombreArchivo;
	private Valor Valor;
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public Valor getValor() {
		return Valor;
	}
	public void setValor(Valor valor) {
		Valor = valor;
	}
	public ResultadoComparacion(String nombreArchivo, Boletin2FicherosFoldesEnum.Valor valor) {
		super();
		this.nombreArchivo = nombreArchivo;
		Valor = valor;
	}
	@Override
	public String toString() {
		return "ResultadoComparacion [nombreArchivo=" + nombreArchivo + ", Valor=" + Valor + "]";
	}
	
	

}

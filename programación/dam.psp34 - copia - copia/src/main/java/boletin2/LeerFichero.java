package boletin2;

import java.io.File;

public class LeerFichero implements Runnable{
	 private File Fichero;
	 private int numCaracteres;
	 
	 

	public LeerFichero() {
		super();
	}



	public LeerFichero(File fichero, int numCaracteres) {
		super();
		Fichero = fichero;
		this.numCaracteres = numCaracteres;
	}



	

	public File getFichero() {
		return Fichero;
	}



	public void setFichero(File fichero) {
		Fichero = fichero;
	}



	public int getNumCaracteres() {
		return numCaracteres;
	}



	public void setNumCaracteres(int numCaracteres) {
		this.numCaracteres = numCaracteres;
	}



	@Override
	public void run() {
		
		//Llamada al método contarcaracteres
		// TODO Auto-generated method stub
		
	}



	@Override
	public String toString() {
		return "LeerFichero [Fichero=" + Fichero + ", numCaracteres=" + numCaracteres + "]";
	}
	

	
	
}

package boletin1;

import java.io.IOException;

public class Notepad {
	public static void main(String[] args){
		Runtime rt = Runtime.getRuntime();
		String[] informacionProceso = {"NotePad.exe"};
		Process proceso;
		try {
			proceso = rt.exec(informacionProceso);
			int codigoRetorno = proceso.waitFor(); //Espero a que termine
			System.out.println("-- Después del wait :"+ codigoRetorno);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}
	}


}

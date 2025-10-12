package boletin1ProcessBuilder;

import java.io.IOException;

public class Ejercicio1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime kernel = Runtime.getRuntime();
		System.out.println(kernel.freeMemory());
		System.out.println();
		System.out.println(kernel.maxMemory());
		System.out.println();

		System.out.println(kernel.totalMemory());

		// abrir un archivo notepadd
		//si le pasas la ruta donde quieras que se guarde lo pones asi por ejemplo c:\\adios2.txt"
		String[] argumentos = { "notepad", "C:\\adios2.txt" };
		String[] notepad = { "notepad" };
		String[] google = { "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe", "youtube.com" };
		try {
			Process proceso = kernel.exec(google);
			try {
				int codigoRetorno = proceso.waitFor();
				System.out.println("eer");
				System.out.println("Después del chrome" + codigoRetorno);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			/*1.Busca la ruta dónde tienes el ejecutable para Chrome 
		y crea un programa que usando Runtime abra una instancia de Chrome.*/
		
		String [] chrome = {"C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe"};
		try {
			kernel.exec(chrome);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

	}

}

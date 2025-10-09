package boletin1;

import java.io.IOException;

public class Ejercicio2 {

	public static void main(String[] args) {
		Runtime kernel = Runtime.getRuntime();
		
		System.out.println(kernel.totalMemory());
		System.out.println(kernel.maxMemory());
		System.out.println(kernel.freeMemory());

		/*String [] argumentos = {"notepad","c:\\adios2.txt"}; // abrir notepad y el documento en concreto que quiero crear o consultar
		//si le pasas la ruta donde quieras que se guarde lo pones asi por ejemplo c:\\adios2.txt"
		try {
			kernel.exec(argumentos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
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

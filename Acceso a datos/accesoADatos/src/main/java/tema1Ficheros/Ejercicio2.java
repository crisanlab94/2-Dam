package tema1Ficheros;

import java.io.File;

public class Ejercicio2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ds"); 
		
		
	}
	

	public void mostrarInformacion(String ruta) {
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			System.out.println("La ruta no existe o no es válida.");
		} else {
			System.out.println("Nombre: " + archivo.getName());
			System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
			System.out.println("Ruta relativa: " + archivo.getPath());
			System.out.println("Se puede leer: " + archivo.canRead());
			System.out.println("Se puede escribir: " + archivo.canWrite());
			System.out.println("Tamaño (bytes): " + archivo.length());
			System.out.println("Es un directorio: " + archivo.isDirectory());
			System.out.println("Es un fichero: " + archivo.isFile());
			System.out.println("Directorio padre: " + archivo.getParent());
		}
	}

}
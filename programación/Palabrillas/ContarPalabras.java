package Palabrillas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ContarPalabras {
	private static final String rutaResources = "src/main/resources/";
	
	public static void main(String[] args) {
		String ruta = rutaResources + args[0];
		String palabra = args[1];
		ContarPalabras a = new ContarPalabras();
		
		  
		try { 
			System.out.println("La palabra a buscar en la ruta"+ruta+" es "+ palabra + " y aparece "+a.contarPalabras(ruta, palabra) + " veces.");
		} catch (FileNotFoundException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	
	public int contarPalabras(String ficheroYRuta, String palabra) throws FileNotFoundException {
		int numPalabras = 0;
		File ficheroS = new File(ficheroYRuta);
		FileReader fichero = new FileReader(ficheroYRuta); 
		Scanner in = new Scanner(fichero);
		while (in.hasNext()) {
			String palFichero = in.next();
			if (palFichero.equalsIgnoreCase(palabra)) {
				numPalabras += 1;
			}
		}
		in.close();

		return numPalabras;
	}

	public void escribirFicheroLog(int nume, String rutaFichero) {
		PrintWriter out = null;
		FileWriter ficheroSalida; 
		File archivoTxt = new File(rutaFichero);
		try {
			ficheroSalida = new FileWriter(rutaFichero);
			out = new PrintWriter(ficheroSalida);
			out.printf("El número de veces que aparece es %d %n", nume);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public void escribirFicheroLog2(int nume, String rutaFichero) {
	    PrintWriter out = null;
	    FileWriter ficheroSalida;
	    File archivoTxt = new File(rutaFichero);

	    try {
	        // Crear el directorio 'resources' si no existe
	        File directorio = archivoTxt.getParentFile();
	        if (directorio != null && !directorio.exists()) {
	            directorio.mkdirs();
	        }

	        ficheroSalida = new FileWriter(archivoTxt);
	        out = new PrintWriter(ficheroSalida);
	        out.printf("El número de veces que aparece es %d %n", nume);
	    } catch (IOException e) {
	        System.err.println("Error al escribir en el fichero: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	    }
	}



}

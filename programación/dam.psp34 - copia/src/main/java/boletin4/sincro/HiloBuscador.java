package boletin4.sincro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

	class HiloBuscador extends Thread {
	    private File fichero;
	    private String palabra;
	    private Contador contador;

	    public HiloBuscador(File fichero, String palabra, Contador contador) {
	        this.fichero = fichero;
	        this.palabra = palabra;
	        this.contador = contador;
	    }

	    @Override
	    public void run() {
	        if (buscarPalabraEnFichero(fichero, palabra)) {
	            contador.incrementar();
	            System.out.println("La palabra '" + palabra + "' aparece en: " + fichero.getName());
	        } else {
	            System.out.println("La palabra '" + palabra + "' NO aparece en: " + fichero.getName());
	        }
	    }

	    // Método sincronizado para la búsqueda en un fichero
	    private synchronized boolean buscarPalabraEnFichero(File fichero, String palabra) {
	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                if (linea.toLowerCase().contains(palabra.toLowerCase())) {
	                    return true;
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}



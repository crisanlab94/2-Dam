package boletin4.sincro;

import java.io.File;


	public class GestionaBusqueda {
	    public static void main(String[] args) {
	        // Directorio donde están los ficheros de texto
	        File directorio = new File("C:/ruta/a/tu/directorio"); // <-- cambia la ruta
	        String palabra = "java"; // palabra a buscar

	        Contador contador = new Contador();

	        if (directorio.isDirectory()) {
	            File[] ficheros = directorio.listFiles((dir, name) -> name.endsWith(".txt"));

	            if (ficheros != null) {
	                Thread[] hilos = new Thread[ficheros.length];

	                for (int i = 0; i < ficheros.length; i++) {
	                    hilos[i] = new HiloBuscador(ficheros[i], palabra, contador);
	                    hilos[i].start();
	                }

	                // Esperamos a que terminen todos los hilos
	                for (Thread hilo : hilos) {
	                    try {
	                        hilo.join();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }

	                System.out.println("\nLa palabra '" + palabra + "' aparece en " + contador.getTotal() + " ficheros.");
	            }
	        } else {
	            System.out.println("La ruta indicada no es un directorio válido.");
	        }
	    }
	}





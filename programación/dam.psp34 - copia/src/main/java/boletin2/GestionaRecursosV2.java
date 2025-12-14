package boletin2;

import java.io.File;


	public class GestionaRecursosV2 {
	    public static void main(String[] args) {
	        File carpeta = new File("resources"); // carpeta resources en tu proyecto
	        ContadorCaracteres contador = new ContadorCaracteres();

	        if (carpeta.isDirectory()) {
	            File[] ficheros = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));

	            if (ficheros != null) {
	                // Creamos un hilo por cada fichero
	                Thread[] hilos = new Thread[ficheros.length];

	                for (int i = 0; i < ficheros.length; i++) {
	                    hilos[i] = new HiloContador(ficheros[i], contador);
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

	                System.out.println("\nNúmero total de caracteres en todos los ficheros: " + contador.getTotal());
	            }
	        } else {
	            System.out.println("La carpeta resources no existe o no es un directorio.");
	        }
	    }
	}



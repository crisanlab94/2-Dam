package boletin2;

import java.util.ArrayList;
import java.util.List;

import Boletin.Mascota;

public class Ejercicio1 {
	   public static void main(String[] args) {

	        Mascota p = new Mascota("Lulú", 0);



	        // Creamos una colección de hilos

	        List<Thread> cuidadores = new ArrayList<Thread>();

	 

	        for (int i = 1; i <= 100; i++) { 

	            Thread cui = new Thread(p, "Cuidador " + i);

	            cuidadores.add(cui);



	            // El cuidador 1 tendrá prioridad 10, el 2 tendrá 9, etc.

	            int prioridad = 11 - i; 

	            if (prioridad < Thread.MIN_PRIORITY) {

	                prioridad = Thread.MIN_PRIORITY; // mínimo permitido es 1

	            }

	            cui.setPriority(prioridad);



	            System.out.println(cui.getName() + " prioridad: " + cui.getPriority());



	            cui.start();

	        }





	        // Mostramos cuántas veces ha comido la mascota

	        System.out.println("La mascota ha comido " + p.getNumVecesCome() + " veces.");

	    }

	}
package Boletin;

import java.util.ArrayList;
import java.util.List;

public class GestionaMascota {
	public static void main(String[] args) {
		
		Mascota m = new Mascota("Lulu", 0);
		//Cuidadores 5
		/**Thread pepe = new Thread(m,"Pepe");
		Thread maria = new Thread(m,"Maria");
		Thread ana = new Thread(m,"Ana");
		Thread lucia = new Thread(m,"Lucia");
		Thread juan = new Thread(m,"Juan");**/
		
		//ahora 100 cuidadores
		
		List<Thread> cuidadores = new ArrayList<Thread>();

		for (int i = 0; i <= 100; i++) {
		    Thread cuidador = new Thread(m, "Cuidador" + i);
		    cuidadores.add(cuidador);

		    if (i % 2 == 0) {
		        cuidador.setPriority(Thread.MAX_PRIORITY);
		    } else {
		        cuidador.setPriority(Thread.MIN_PRIORITY);
		    }
 
		    cuidador.start();
		}

		// Esperar a que todos los hilos terminen
		for (Thread thread : cuidadores) {
		    try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Veces que come la mascota: " + m.getNumVecesCome());
		
		/**
		pepe.start();
		maria.start();
		ana.start();
		lucia.start();
		juan.start();
		try {
			//El padre espera que termine cada hilo
			pepe.join();
			maria.join();
			ana.join();
			lucia.join();
			juan.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		**/
	
		
	

	}
}

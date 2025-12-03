package BoletinSemaforos;
import java.util.concurrent.Semaphore;

import java.util.ArrayList;
import java.util.List;

public class Gasolinera {
	public static void main(String[] args) {
		Semaphore sema = new Semaphore(3); 
		/*
		SemaforoCoche semaforo = new SemaforoCoche(3);
		List<Thread> listaCoches = new ArrayList<Thread>();

		

		for (int i = 0; i < 8; i++) { 
			listaCoches.add(new Thread(new Coche("Coche " + (i + 1), semaforo)));
		}

		for (Thread coche : listaCoches) { 
			coche.start();

		}
		*/
		
		List<Thread> listaCochesJava = new ArrayList<Thread>(); 
		for (int i = 0; i < 8; i++) { 
			listaCochesJava.add(new Thread(new CocheSemaforoJava("Coche " + (i + 1), sema))); 
		}

		for (Thread cochejaVa : listaCochesJava) {
			cochejaVa.start(); 

		}
		


	}
}
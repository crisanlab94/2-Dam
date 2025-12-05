package boletinRestaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;



public class GestionaRestaurante {
	public static void main(String[] args) {
		//Lista vacia para guardar todos los hilos (cocinero y comensales)
		List <Thread> hilos = new ArrayList<>();
		//Semaforo con 3 permisos
		 Semaphore semaforo = new Semaphore(3);
	       try {
	    	   semaforo.acquire(3); // vaciado manual 3-3 = 0, el cocinero pone los permisos y empieza primero
	       } catch (InterruptedException e) {
	    	   e.printStackTrace();
	       }
	       
	       //Creamos al cocinero
	       HiloCocinero cocinero = new HiloCocinero("Cocinero",semaforo);
	       //creamos el hilo del cocinero
	       Thread cocineroHilo = new Thread(cocinero);
	       hilos.add(cocineroHilo);
	       
	       //Creamos a los comensales, en este caso 3
	        for (int i = 0; i < 3; i++) {
	        	//Añadimos comensal
	        	HiloComensal comensal = new HiloComensal("Comensal" + i, semaforo);
	        	//Creamos hilo comensal
	        	Thread comensalHilo = new Thread(comensal);
	        	hilos.add(comensalHilo);
	        }

	        //Recorremos la lista de cocinero y comensales
	        //coge el 1º hilo de la litsa(cocinero)
	        //coger el 2º (comensal)...
	        for (Thread h : hilos) {
	            h.start();
	        }
	        
	        
	    }
}


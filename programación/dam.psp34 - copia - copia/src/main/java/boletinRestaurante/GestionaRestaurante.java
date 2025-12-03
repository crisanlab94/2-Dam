package boletinRestaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;



public class GestionaRestaurante {
	public static void main(String[] args) {
		List <Thread> hilos = new ArrayList<>();
		 Semaphore semaforo = new Semaphore(3);
	       try {
	    	   semaforo.acquire(3);
	       } catch (InterruptedException e) {
	    	   e.printStackTrace();
	       }
	       
	       HiloCocinero cocinero = new HiloCocinero("Cocinero",semaforo);
	       Thread cocineroHilo = new Thread(cocinero);
	       hilos.add(cocineroHilo);
	       
	        for (int i = 0; i < 6; i++) {
	        	HiloComensal comensal = new HiloComensal("Comensal" + i, semaforo);
	        	Thread comensalHilo = new Thread(comensal);
	        	hilos.add(comensalHilo);
	        }

	     
	        for (Thread h : hilos) {
	            h.start();
	        }
	        
	        
	    }
}


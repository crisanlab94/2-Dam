package gimnasio;

import java.util.concurrent.Semaphore;

public class Gimnasio {
	    private Semaphore sem;

	    public Gimnasio(int aforo) {
	        this.sem = new Semaphore(aforo);
	    }

	    public boolean entrar(Cliente c) {
	        boolean haEntrado = false; // Variable para el único return

	        // Intentamos coger plaza sin bloquear (Aforo con rechazo)
	        if (sem.tryAcquire()) { 
	            System.out.println("Cliente " + c.getId() + " dentro.");
	            haEntrado = true;
	        } else {
	            System.out.println("Gimnasio lleno. Cliente " + c.getId() + " se va.");
	        }

	        return haEntrado; 
	    }

	    public void salir() {
	        sem.release(); // Devuelve la plaza
	    }
	}


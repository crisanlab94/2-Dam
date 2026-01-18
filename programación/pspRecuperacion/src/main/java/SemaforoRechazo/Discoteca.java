package SemaforoRechazo;

import java.util.concurrent.Semaphore;

public class Discoteca {
	// RECURSO
	
	    private Semaphore sem;
	    public Discoteca(Semaphore sem) { this.sem = sem; }

	    public void intentarEntrar(String nombre) {
	        if (sem.tryAcquire()) { // MODELO RECHAZO: No espera
	            try {
	                System.out.println(nombre + " dentro bailando.");
	                Thread.sleep(2000);
	            } catch (InterruptedException e) { } 
	            finally { sem.release(); }
	        } else {
	            System.out.println(nombre + " se va a otra disco (Lleno).");
	        }
	    }
	}

	



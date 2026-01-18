package semaforoBloqueo;

import java.util.concurrent.Semaphore;

public class GestionaCajero {
	
	    public static void main(String[] args) {
	        Semaphore s = new Semaphore(1); // SOLO 1
	        Cajero c = new Cajero(s);
	        for (int i = 1; i <= 5; i++) {
	            new Thread(new Persona("Persona " + i, c)).start();
	        }
	    }
	}



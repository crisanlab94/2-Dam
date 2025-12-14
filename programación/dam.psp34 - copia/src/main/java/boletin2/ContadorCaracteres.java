
	package boletin2;

	import java.io.*;
	import java.util.concurrent.Semaphore;

	class ContadorCaracteres {
	    private int total = 0;

	    // Método sincronizado para evitar condiciones de carrera
	    public synchronized void sumar(int cantidad) {
	        total += cantidad;
	    }

	    public int getTotal() {
	        return total;
	    }
	}

	
	





	package boletin4.sincro;

	import java.io.*;
	import java.util.concurrent.atomic.AtomicInteger;

	class Contador {
	    private int total = 0;

	    // Método sincronizado para evitar condiciones de carrera
	    public synchronized void incrementar() {
	        total++;
	    }

	    public int getTotal() {
	        return total;
	    }
	}

	

	


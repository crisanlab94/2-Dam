package semaforoCadena;

import java.util.concurrent.Semaphore;

public class Lavadero {
	    private Semaphore semAspirado;
	    private Semaphore semLavado;

	    public Lavadero(Semaphore s1, Semaphore s2) {
	        this.semAspirado = s1;
	        this.semLavado = s2;
	    }

	    public Semaphore getSemAspirado() { return semAspirado; }
	    public Semaphore getSemLavado() { return semLavado; }
	}



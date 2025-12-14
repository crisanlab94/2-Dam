
	package simulacroLavadoCoches;

	import java.util.concurrent.Semaphore;

	public class LavadoraB implements Runnable {
	    private String nombre;
	    private Semaphore hayCoches;
	    private Semaphore hayTurno;
	    private int numCoches;
	    private int tiempoLavado;

	    public LavadoraB(String nombre, Semaphore hayCoches, Semaphore hayTurno, int numCoches, int tiempoLavado) {
	        this.nombre = nombre;
	        this.hayCoches = hayCoches;
	        this.hayTurno = hayTurno;
	        this.numCoches = numCoches;
	        this.tiempoLavado = tiempoLavado;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numCoches; i++) {
	            try {
	                hayCoches.acquire(); // esperar coche
	                System.out.println(nombre + " lava coche " + (i+1));
	                Thread.sleep(tiempoLavado); // simula tiempo de lavado
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



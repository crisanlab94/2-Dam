
	package simulacroLavadoCoches;

	import java.util.concurrent.Semaphore;

	public class CocheB implements Runnable {
	    private String nombre;
	    private Semaphore hayCoches;
	    private Semaphore hayTurno;

	    public CocheB(String nombre, Semaphore hayCoches, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayCoches = hayCoches;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	            hayTurno.acquire(); // esperar turno
	            System.out.println(nombre + " espera para ser lavado");
	            hayCoches.release(); // avisar a las máquinas
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}




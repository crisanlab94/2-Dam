
	package SimulacroGimnasio;

	import java.util.concurrent.Semaphore;

	public class ClienteGimnasio implements Runnable {
	    private String nombre;
	    private Semaphore hayPlazas;
	    private Semaphore hayTurno;

	    public ClienteGimnasio (String nombre, Semaphore hayPlazas, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayPlazas = hayPlazas;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	            // Controlar orden de llegada
	            hayTurno.acquire();

	            // Intentar ocupar plaza
	            hayPlazas.acquire(); // aquí bloquea si no hay plazas libres

	            System.out.println(nombre + " se ha apuntado a la clase.");

	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            // liberar turno para el siguiente cliente
	            hayTurno.release();
	        }
	    }
	}




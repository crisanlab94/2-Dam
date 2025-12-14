
	package SimulacroGimnasio;

	import java.util.concurrent.Semaphore;

	public class ClienteGimnasioA implements Runnable {
	    private String nombre;
	    private Semaphore hayPlazas;
	    private Semaphore hayTurno;

	    public ClienteGimnasioA(String nombre, Semaphore hayPlazas, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayPlazas = hayPlazas;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	            hayTurno.acquire(); // esperar turno
	            if (hayPlazas.tryAcquire()) { // intentar ocupar plaza
	                System.out.println(nombre + " se ha apuntado a la clase.");
	            } else {
	                System.out.println(nombre + " no ha conseguido plaza.");
	            }
	            hayTurno.release(); // liberar turno
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}




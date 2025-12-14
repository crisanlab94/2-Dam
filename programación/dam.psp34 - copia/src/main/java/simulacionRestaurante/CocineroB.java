
	package simulacionRestaurante;

	import java.util.concurrent.Semaphore;

	public class CocineroB implements Runnable {
	    private String nombre;
	    private Semaphore hayPedidos;
	    private Semaphore hayTurno;
	    private int numPedidos;
	    private int tiempoCocina;

	    public CocineroB(String nombre, Semaphore hayPedidos, Semaphore hayTurno, int numPedidos, int tiempoCocina) {
	        this.nombre = nombre;
	        this.hayPedidos = hayPedidos;
	        this.hayTurno = hayTurno;
	        this.numPedidos = numPedidos;
	        this.tiempoCocina = tiempoCocina;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numPedidos; i++) {
	            try {
	                hayPedidos.acquire(); // esperar pedido
	                System.out.println(nombre + " cocina pedido " + (i+1));
	                Thread.sleep(tiempoCocina); // simula tiempo de cocina
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}




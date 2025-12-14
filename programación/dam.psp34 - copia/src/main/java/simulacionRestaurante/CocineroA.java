
	package simulacionRestaurante;

	import java.util.concurrent.Semaphore;

	public class CocineroA implements Runnable {
	    private String nombre;
	    private Semaphore hayPedidos;
	    private Semaphore hayTurno;
	    private int numPedidos;

	    public CocineroA(String nombre, Semaphore hayPedidos, Semaphore hayTurno, int numPedidos) {
	        this.nombre = nombre;
	        this.hayPedidos = hayPedidos;
	        this.hayTurno = hayTurno;
	        this.numPedidos = numPedidos;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numPedidos; i++) {
	            try {
	                hayPedidos.acquire(); // esperar pedido
	                System.out.println(nombre + " cocina pedido " + (i+1));
	                Thread.sleep(4000); // tarda 4s en cocinar
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}




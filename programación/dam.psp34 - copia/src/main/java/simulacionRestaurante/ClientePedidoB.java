
	package simulacionRestaurante;

	import java.util.concurrent.Semaphore;

	public class ClientePedidoB implements Runnable {
	    private String nombre;
	    private Semaphore hayPedidos;
	    private Semaphore hayTurno;

	    public ClientePedidoB(String nombre, Semaphore hayPedidos, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayPedidos = hayPedidos;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	        	 hayPedidos.release(); // avisar al cocinero
	        	   System.out.println(nombre + " ha hecho un pedido");
	            hayTurno.acquire(); // esperar turno
	           
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}



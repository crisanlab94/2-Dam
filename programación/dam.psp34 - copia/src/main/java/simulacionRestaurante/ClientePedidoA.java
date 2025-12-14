

	package simulacionRestaurante;

	import java.util.concurrent.Semaphore;

	public class ClientePedidoA implements Runnable {
	    private String nombre;
	    private Semaphore hayPedidos;
	    private Semaphore hayTurno;

	    public ClientePedidoA(String nombre, Semaphore hayPedidos, Semaphore hayTurno) {
	        this.nombre = nombre;
	        this.hayPedidos = hayPedidos;
	        this.hayTurno = hayTurno;
	    }

	    @Override
	    public void run() {
	        try {
	            hayTurno.acquire(); // esperar turno
	            System.out.println(nombre + " ha hecho un pedido");
	            hayPedidos.release(); // avisar al cocinero
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}




	package simulacionRestaurante;

	import java.util.concurrent.Semaphore;

	public class GestionaRestauranteA {
	    public static void main(String[] args) {
	        final int numPedidos = 15;

	        // Inicializamos en 1 y luego bajamos a 0
	        Semaphore hayPedidos = new Semaphore(1);
	        Semaphore hayTurno   = new Semaphore(1);

	        try {
	            hayPedidos.acquire(); // ponerlo en 0
	            hayTurno.acquire();   // ponerlo en 0
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("--- INICIO DE LA JORNADA CON UN COCINERO ---");
	        long inicio = System.currentTimeMillis();

	        CocineroA cocinero = new CocineroA("Cocinero 1", hayPedidos, hayTurno, numPedidos);
	        Thread hiloCocinero = new Thread(cocinero);
	        hiloCocinero.start();

	        // Clientes que hacen pedidos
	        for (int i = 1; i <= numPedidos; i++) {
	            ClientePedidoA cliente = new ClientePedidoA("Cliente " + i, hayPedidos, hayTurno);
	            new Thread(cliente).start();
	        }

	        try {
	            hiloCocinero.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de preparación de %d pedidos: %.2f segundos\n",
	                          numPedidos, (fin - inicio)/1000.0);
	    }
	}



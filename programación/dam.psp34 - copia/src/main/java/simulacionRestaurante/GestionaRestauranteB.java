
	package simulacionRestaurante;

	import java.util.concurrent.Semaphore;

	public class GestionaRestauranteB {
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

	        System.out.println("--- INICIO DE LA JORNADA CON DOS COCINEROS ---");
	        long inicio = System.currentTimeMillis();

	        CocineroB cocinero1 = new CocineroB("Cocinero 1 (4s)", hayPedidos, hayTurno, numPedidos, 4000);
	        CocineroB cocinero2 = new CocineroB("Cocinero 2 (6s)", hayPedidos, hayTurno, numPedidos, 6000);

	        Thread hiloCocinero1 = new Thread(cocinero1);
	        Thread hiloCocinero2 = new Thread(cocinero2);

	        hiloCocinero1.start();
	        hiloCocinero2.start();

	        // Clientes que hacen pedidos
	        for (int i = 1; i <= numPedidos; i++) {
	            ClientePedidoB cliente = new ClientePedidoB("Cliente " + i, hayPedidos, hayTurno);
	            new Thread(cliente).start();
	        }

	        try {
	            hiloCocinero1.join();
	            hiloCocinero2.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        long fin = System.currentTimeMillis();
	        System.out.printf("Tiempo total de preparación de %d pedidos: %.2f segundos\n",
	                          numPedidos, (fin - inicio)/1000.0);
	    }
	}




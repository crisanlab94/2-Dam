
	package SimulacroGimnasio;

	import java.util.concurrent.Semaphore;

	public class GestionaGimnasioA {
	    public static void main(String[] args) {
	        final int numClientes = 30; // llegan 30 personas
	        final int plazas = 20;      // solo hay 20 plazas

	        Semaphore hayPlazas = new Semaphore(plazas); // plazas disponibles
	        Semaphore hayTurno  = new Semaphore(1);      // orden de llegada

	        System.out.println("--- INSCRIPCIÓN POR ORDEN DE LLEGADA ---");

	        for (int i = 1; i <= numClientes; i++) {
	            ClienteGimnasioA cliente = new ClienteGimnasioA("Cliente " + i, hayPlazas, hayTurno);
	            new Thread(cliente).start();
	        }
	    }
	}




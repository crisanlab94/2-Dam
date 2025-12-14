
	package SimulacroGimnasio;

	import java.util.concurrent.Semaphore;

	public class GestionaGimnasio {
	    public static void main(String[] args) {
	        final int plazas = 20; // capacidad fija de la clase

	        // Inicializamos en 1 y luego bajamos a 0
	        Semaphore hayPlazas = new Semaphore(plazas); // 20 plazas disponibles
	        Semaphore hayTurno  = new Semaphore(1);

	        try {
	            hayTurno.acquire(); // ponerlo en 0 al inicio
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        System.out.println("--- INSCRIPCIÓN POR ORDEN DE LLEGADA (20 plazas) ---");

	        // Simulamos llegada de muchos clientes (más de 20)
	        for (int i = 1; i <= 35; i++) {
	            ClienteGimnasioA cliente = new ClienteGimnasioA("Cliente " + i, hayPlazas, hayTurno);
	            new Thread(cliente).start();
	        }
	    }
	}




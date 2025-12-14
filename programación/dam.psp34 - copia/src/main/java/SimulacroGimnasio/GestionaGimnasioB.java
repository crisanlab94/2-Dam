
	package SimulacroGimnasio;

	public class GestionaGimnasioB {
	    public static void main(String[] args) {
	        final int numClientes = 30;
	        final int plazas = 20;

	        GestorInscripciones gestor = new GestorInscripciones(plazas);

	        System.out.println("--- INSCRIPCIÓN POR PRIORIDAD ---");

	        for (int i = 1; i <= numClientes; i++) {
	            int prioridad = (i % 5 == 0) ? 1 : 5; // cada 5º cliente es VIP
	            ClienteGimnasioB cliente = new ClienteGimnasioB("Cliente " + i, prioridad, gestor);
	            new Thread(cliente).start();
	        }
	    }
	}




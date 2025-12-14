
	package SimulacroGimnasio;

	import java.util.PriorityQueue;

	public class GestorInscripciones {
	    private int plazas;
	    private PriorityQueue<ClienteGimnasioB> cola;

	    public GestorInscripciones(int plazas) {
	        this.plazas = plazas;
	        this.cola = new PriorityQueue<>();
	    }

	    public synchronized void apuntar(ClienteGimnasioB cliente) {
	        if (cola.size() < plazas) {
	            cola.add(cliente);
	            System.out.println(cliente.getNombre() + " se ha apuntado con prioridad.");
	        } else {
	            System.out.println(cliente.getNombre() + " no ha conseguido plaza.");
	        }
	    }
	}




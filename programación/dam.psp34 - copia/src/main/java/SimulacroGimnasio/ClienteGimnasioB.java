
	package SimulacroGimnasio;

	public class ClienteGimnasioB implements Comparable<ClienteGimnasioB>, Runnable {
	    private String nombre;
	    private int prioridad; // menor número = más prioridad
	    private GestorInscripciones gestor;

	    public ClienteGimnasioB(String nombre, int prioridad, GestorInscripciones gestor) {
	        this.nombre = nombre;
	        this.prioridad = prioridad;
	        this.gestor = gestor;
	    }

	    @Override
	    public void run() {
	        gestor.apuntar(this);
	    }

	    @Override
	    public int compareTo(ClienteGimnasioB otro) {
	        return Integer.compare(this.prioridad, otro.prioridad);
	    }

	    public String getNombre() {
	        return nombre;
	    }
	}



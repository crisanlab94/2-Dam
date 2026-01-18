package monitorSimple;


	public class Urna {
	    private int votos = 0;

	    // Solo un hilo puede entrar aquí a la vez
	    public synchronized void sumarVoto() {
	        votos++;
	    }

	    public void mostrar() { System.out.println("Total: " + votos); }
	}



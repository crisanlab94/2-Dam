package monitorSimple;


	public class Ordenador implements Runnable {
	    private Urna urna;
	    public Ordenador(Urna urna) { this.urna = urna; }
	    @Override
	    public void run() {
	        for(int i=0; i<100; i++) {
	            urna.sumarVoto(); // Protegido por el monitor
	        }
	    }
	}


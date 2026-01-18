package monitorWaitIndidivual;



	public class Cliente implements Runnable {
	    private String nombre;
	    private Restaurante res;

	    public Cliente(String nombre, Restaurante res) {
	        this.nombre = nombre;
	        this.res = res;
	    }

	    @Override
	    public void run() {
	        // Cada cliente entra, come UNA VEZ y el hilo termina
	        res.comerPlato(this.nombre);
	    }
	}



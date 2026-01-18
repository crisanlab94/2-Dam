package monitorWaitIndidivual;



	public class Cocinero implements Runnable {
	    private Restaurante res;
	    private int totalAComer;

	    public Cocinero(Restaurante res, int totalAComer) {
	        this.res = res;
	        this.totalAComer = totalAComer;
	    }

	    @Override
	    public void run() {
	        // El cocinero debe cocinar tantas veces como clientes haya
	        for (int i = 1; i <= totalAComer; i++) {
	            res.ponerPlato("Chef Pepe");
	        }
	        System.out.println("Chef Pepe: 'He terminado mi jornada'.");
	    }
	}



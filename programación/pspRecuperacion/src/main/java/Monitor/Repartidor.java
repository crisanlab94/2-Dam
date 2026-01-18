package Monitor;


	// Ejemplo para Camionero (el que descarga)
	public class Repartidor implements Runnable {
	    private Almacen almacen;
	    public Repartidor(Almacen a) { this.almacen = a; }
	    @Override
	    public void run() {
	        try { almacen.descargar(); } catch (InterruptedException e) {}
	    }
	}



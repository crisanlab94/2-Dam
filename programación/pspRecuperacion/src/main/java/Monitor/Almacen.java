package Monitor;

public class Almacen {

	    private int cajas = 0;
	    private int MAX = 5;

	    public synchronized void descargar() throws InterruptedException {
	        while (cajas >= MAX) { 
	            wait(); // El camionero se duerme hasta que haya hueco
	        }
	        cajas++;
	        System.out.println("Caja metida. Total: " + cajas);
	        notifyAll(); // Avisa a los repartidores
	    }

	    public synchronized void sacar() throws InterruptedException {
	        while (cajas <= 0) { 
	            wait(); // El repartidor se duerme hasta que haya cajas
	        }
	        cajas--;
	        System.out.println("Caja sacada. Quedan: " + cajas);
	        notifyAll(); // Avisa a los camioneros
	    }
	}



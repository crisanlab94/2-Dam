package Boletin;


	public class Ejercicio2B implements Runnable {
	    @Override
	    public void run() {
	        while (true) {
	            System.out.println("PROCESOS");
	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}



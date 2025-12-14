package Boletin;


	public class GestionaEjercicio2 {
	    public static void main(String[] args) {
	        Thread hilo1 = new Thread(new Ejercicio2A());
	        Thread hilo2 = new Thread(new Ejercicio2B());

	        hilo1.start();
	        hilo2.start();
	    }

}

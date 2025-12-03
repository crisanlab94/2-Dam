package botetin3;

public class GestionaHilosCooperativos {
	public static void main(String[] args) {
		
	long t_comienzo = System.currentTimeMillis();
	MultiploCooperativos objetoMultiplo = new MultiploCooperativos();
	try {
		// Creo un hilo de cada tipo
		MultiploCoopHilo2 hilo2 = new MultiploCoopHilo2(objetoMultiplo);
		MultiploCoopHilo3 hilo3 = new MultiploCoopHilo3(objetoMultiplo);
		MultiploCoopHilo7 hilo7 = new MultiploCoopHilo7(objetoMultiplo);
	
		hilo2.start(); 
		hilo3.start();
		hilo7.start();
		hilo2.join();
		hilo3.join();
		hilo7.join();
	} catch (InterruptedException e) {
		System.out.println(e.getMessage());
	}
	long t_fin = System.currentTimeMillis();
	long tiempototal = t_fin - t_comienzo;
	System.out.println("El proceso total ha tardado= " + tiempototal + "mseg");
}


}

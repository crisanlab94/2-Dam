package hilos.Boletin1;

public class GestionaEjercicio1 {
	public static void main(String[] args) {
		Ejercicio1a hilo1 = new Ejercicio1a("Hilo1");
		hilo1.start();
       Ejercicio1b hilo2 = new Ejercicio1b("Hilo2");
       hilo2.start();
       
       System.out.println("Termina el padre");
	
}

}

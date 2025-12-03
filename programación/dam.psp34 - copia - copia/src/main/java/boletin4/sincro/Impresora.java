package boletin4.sincro;

public class Impresora {
	
		public void imprime(String nombreHilo)
		{
			System.out.println(nombreHilo + " está imprimiendo.");
			try {
				// Simulamos la operación de acceso que lleva algo de tiempo
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(nombreHilo + " ha terminado de imprimir.");
		}
}


package boletin4.sincro;

public class SemaforoBinario {

		private boolean estaLibre; // Definimos una bandera que nos dice si el acceso está libre
		public SemaforoBinario() {
			super();
			estaLibre = true;
		}
		public synchronized void adquirir() throws InterruptedException {
			while (!estaLibre) {
				wait();
			}
			estaLibre =false ;
		}
		public synchronized void liberar() {
			estaLibre = true;
			notifyAll(); // Notificamos
		}
}




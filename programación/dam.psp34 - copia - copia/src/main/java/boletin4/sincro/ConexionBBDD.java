package boletin4.sincro;

import java.util.concurrent.Semaphore;

public class ConexionBBDD {
		private Semaphore semaforo;

		public ConexionBBDD(int numeroConexiones) {
			semaforo = new Semaphore(numeroConexiones);
		}
		public void conexion() {
			try {
				semaforo.acquire();
				System.out.println(Thread.currentThread().getName() + " tiene conexión");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
				System.out.println(Thread.currentThread().getName() + " libera conexión");
			}}
		}



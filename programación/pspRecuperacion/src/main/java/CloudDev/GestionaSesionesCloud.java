package CloudDev;

import java.util.concurrent.Semaphore;



public class GestionaSesionesCloud {
	public static void main(String[] args) {
		// Iniciamos los semáforos con el aforo 
        Semaphore semDE = new Semaphore(20);
        Semaphore semEF = new Semaphore(8);

     // Creamos los entornos
        EntornoCloud entornoDE = new EntornoCloud(Tipo.ED, 20);
        EntornoCloud entornoEF = new EntornoCloud(Tipo.EP, 8);

     // Lanzamos los hilos (35 para DE)
        for (int i = 1; i <= 35; i++) {
            // Pasamos el semáforo común semDE a todos estos desarrolladores
            Thread t = new Thread(new Desarrollador("D-" + i, entornoDE, semDE));
            t.start(); // Crea el contexto del hilo y pasa a runnable 
        }

        // Lanzamos los hilos (15 para EF)
        for (int i = 1; i <= 15; i++) {
            Thread t = new Thread(new Desarrollador("P-" + i, entornoEF, semEF));
            t.start();
	}
}
}

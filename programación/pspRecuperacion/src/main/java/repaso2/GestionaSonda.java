package repaso2;

import java.util.concurrent.Semaphore;



public class GestionaSonda {
	public static void main(String[] args) {
		// Iniciamos los semáforos con el ancho de banda
        Semaphore semVideo = new Semaphore(3);
        Semaphore semTelemetria = new Semaphore(6);

     // Creamos los entornos
        EstacionControl estacionVideo = new EstacionControl (TipoTransmision.VIDEO,3);
        EstacionControl estacionTelemetria = new EstacionControl (TipoTransmision.TELEMETRIA,6);


     // Lanzamos los hilos (8 para video)
        for (int i = 1; i <= 8; i++) {
            // Pasamos el semáforo común semDE a todos estos desarrolladores
            Thread t = new Thread(new Sonda("S-" + i, estacionVideo, semVideo));
            t.start(); // Crea el contexto del hilo y pasa a runnable 
        }

        // Lanzamos los hilos (10 para telemetria)
        for (int i = 1; i <= 10; i++) {
            Thread t = new Thread(new Sonda("S-" + i, estacionTelemetria, semTelemetria));
            t.start();
	}
}
	}



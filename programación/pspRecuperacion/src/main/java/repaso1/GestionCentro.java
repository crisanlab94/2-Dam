package repaso1;

import java.util.concurrent.Semaphore;



public class GestionCentro {
	public static void main(String[] args) {
		// Iniciamos los semáforos con el aforo 
        Semaphore semMasaje = new Semaphore(4);
        Semaphore semRehabilitacion = new Semaphore(2);

     // Creamos los entornos
        SalaFisio salaMasaje = new SalaFisio(TipoTratamiento.MENSAJE,4);
        SalaFisio salaRehabilitacion = new SalaFisio(TipoTratamiento.REHABILITACION,2);
        

     // Lanzamos los hilos (10 para masaje)
        for (int i = 1; i <= 10; i++) {
            // Pasamos el semáforo común semMsaje a todos estos atletas
            Thread t = new Thread(new Atleta("A-" + i, salaMasaje, semMasaje));
            t.start(); // Crea el contexto del hilo y pasa a runnable 
        }

        // Lanzamos los hilos (5 para rehabilitacion)
        for (int i = 1; i <= 5; i++) {
            Thread t = new Thread(new Atleta("A-" + i, salaRehabilitacion, semRehabilitacion));
            t.start();
	}
	}

}

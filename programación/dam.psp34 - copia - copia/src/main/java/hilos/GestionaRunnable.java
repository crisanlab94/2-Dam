package hilos;

import java.util.ArrayList;
import java.util.List;

public class GestionaRunnable {

    public static void main(String[] args) {
        long inicioPadre = System.currentTimeMillis();

        // Creamos una colección de hilos
        List<Thread> hilos = new ArrayList<Thread>();

        // Añadimos los hilos hijos a la lista
        for (int i = 1; i <= 10; i++) {
            hilos.add(new Thread(new HiloRunnable("Hilo " + i)));
        } 

        // Iniciamos todos los hilos
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // El padre espera a que todos los hijos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long finPadre = System.currentTimeMillis();
        long tiempoPadre = finPadre - inicioPadre;
        
        

        System.out.println("El hilo padre terminó después de esperar a todos sus hijos.");
        System.out.println("Tiempo total del hilo padre: " + tiempoPadre + " ms");
    }
}

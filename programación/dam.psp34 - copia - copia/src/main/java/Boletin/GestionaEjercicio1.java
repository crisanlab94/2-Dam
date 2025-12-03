package Boletin;

public class GestionaEjercicio1 {

    public static void main(String[] args) {
        long inicioPadre = System.currentTimeMillis();

        Ejercicio1A hilo1 = new Ejercicio1A("Hilo 1");
        Ejercicio1B hilo2 = new Ejercicio1B("Hilo 2");

        hilo1.start();
        hilo2.start();

        try {
            // El padre espera a que los hijos terminen
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finPadre = System.currentTimeMillis();
        long tiempoPadre = finPadre - inicioPadre;

        System.out.println("El hilo padre terminó después de esperar a todos sus hijos.");
        System.out.println("Tiempo total del hilo padre: " + tiempoPadre + " ms");
    }
}

package Boletin;

public class Ejercicio2A implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("SERVICIOS");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
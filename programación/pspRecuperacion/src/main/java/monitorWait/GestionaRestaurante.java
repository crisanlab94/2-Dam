package monitorWait;

public class GestionaRestaurante {
    public static void main(String[] args) {
        Restaurante res = new Restaurante();

        Thread t1 = new Thread(new Cocinero(res));
        Thread t2 = new Thread(new Cliente(res));

        t1.start();
        t2.start();

        try {
            t1.join(); // El Main se queda parado aquí hasta que t1 termine su for
            t2.join(); // El Main se queda parado aquí hasta que t2 termine su for
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- RESTAURANTE CERRADO: Todos han terminado ---");
    }
}



package mapaAlmacen;

public class LanzadorAlmacen {
    public static void main(String[] args) throws InterruptedException {
        // Lanzamos varios hilos de clientes (repartidores)
        Thread t1 = new Thread(new ClienteAlmacen(1, "RECIBIDO", true));
        Thread t2 = new Thread(new ClienteAlmacen(2, "EN_REPARTO", false));
        Thread t3 = new Thread(new ClienteAlmacen(3, "ENTREGADO", true));
        Thread t4 = new Thread(new ClienteAlmacen(1, "EN_REPARTO", true)); // El 1 cambia de estado

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Esperamos a que terminen para asegurar que el servidor procesó todo
        t1.join(); t2.join(); t3.join(); t4.join();
        
        System.out.println("Todos los repartidores han enviado sus datos.");
    }
}
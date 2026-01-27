package EjemploMapaNumero;

public class LanzadorEntero {
	public static void main(String[] args) {
        // Creamos clientes usando IDs numéricos
        // El cliente 101 envía 50 puntos
        Thread t1 = new Thread(new HiloClienteEntero(101, 50));
        
        // El cliente 102 envía 30 puntos
        Thread t2 = new Thread(new HiloClienteEntero(102, 30));
        
        // El cliente 101 vuelve a conectar para enviar 20 puntos más
        // En el servidor, el ID 101 debería acabar con 70 puntos
        Thread t3 = new Thread(new HiloClienteEntero(101, 20));

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Lanzador Entero: Todos los hilos están en marcha.");
    }

}

package EjemploMapaNumero;

public class LanzadorString {
	public static void main(String[] args) {
        // Creamos 3 clientes que son 3 hilos distintos
        // Ana y Juan se conectan por primera vez
        Thread t1 = new Thread(new HiloClienteNombre("Ana", 10));
        Thread t2 = new Thread(new HiloClienteNombre("Juan", 20));
        
        // Ana se conecta OTRA VEZ con 5 puntos más
        // Esto sirve para probar que el mapa SUMA (10 + 5 = 15)
        Thread t3 = new Thread(new HiloClienteNombre("Ana", 5));
        Thread t4 = new Thread(new HiloClienteNombre("Ana", 5));
        // Los lanzamos todos a la vez
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        System.out.println("Lanzador String: Todos los hilos están en marcha.");
    }

}

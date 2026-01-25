package Ejercicio3;

public class LanzadorCliente3 {
    public static void main(String[] args) {
        // Lanzamos 5 clientes para probar que el contador sube bien
        for (int i = 1; i <= 5; i++) {
            HiloCliente3 c = new HiloCliente3();
            c.start();
            
            try {
                // Un pequeño respiro entre clientes
                Thread.sleep(300); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
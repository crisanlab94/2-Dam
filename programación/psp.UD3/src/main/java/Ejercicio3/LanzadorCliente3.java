package Ejercicio3;

public class LanzadorCliente3 {
    public static void main(String[] args) {
    
        for (int i = 1; i <= 5; i++) {
            HiloCliente3 c = new HiloCliente3();
            c.start();
            
            try {
              
                Thread.sleep(300); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
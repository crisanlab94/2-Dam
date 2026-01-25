package Ejercicio2;


public class LanzaServidor2 {
	public static void main(String[] args) {
		
        for (int i = 1; i <= 3; i++) {
            ClienteHilo2 c = new ClienteHilo2();
            c.start();
        }

       
        try {
            Thread.sleep(15000); 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }

      
        new ClienteHilo2().start();
    }
} 
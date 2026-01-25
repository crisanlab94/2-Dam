package Ejercicio4;


public class LanzaClientes {
    public static void main(String[] args) {
       
        for (int i = 1; i <= 3; i++) {
            new ClienteTablero().start();
            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
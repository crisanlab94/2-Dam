package Ejercicio2;

import java.io.IOException;
import java.net.Socket;

public class HiloServidor2 implements Runnable {
    private Socket cliente; 
    
    public HiloServidor2(Socket cliente) { 
        this.cliente = cliente; 
    }
 
    @Override
    public void run() { 
    	try {
           
            Thread.sleep(10000); 
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("=>Desconecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
            try {
                if (cliente != null) cliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar: " + e.getMessage());
            }
    }
}
}

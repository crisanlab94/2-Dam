package Ejercicio0;

import java.io.IOException;
import java.net.Socket;

public class HiloServidor implements Runnable {
    private Socket cliente; 
    public HiloServidor(Socket cliente) { 
        this.cliente = cliente; 
    }
 
    @Override
    public void run() { 
        try {
            System.out.println("Conexión establecida con el cliente " + cliente.getInetAddress());
			Thread.sleep(5000);
	        System.out.println("Conexión finalizada con el cliente " + cliente.getInetAddress());

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            if (cliente != null) cliente.close();
        } catch (IOException e) {
            System.err.println("Error cerrando cliente " + cliente.getInetAddress() + ": " + e.getMessage());
        }
    }
}
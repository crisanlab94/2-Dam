package Ejercicio2;

import java.io.IOException;
import java.net.Socket;


public class ClienteHilo2 extends Thread {
	  @Override
	    public void run() { 
		  String Host = "localhost"; 
			int Puerto = 44444;     
			Socket cliente = null;
			try  {
				cliente = new Socket(Host, Puerto);
				Thread.sleep(10000);
			} catch (IOException | InterruptedException e) {
	            System.err.println("Error en el cliente: " + e.getMessage());
			} finally {
				
			    try {
			
			        if (cliente != null) {
			
			            cliente.close();
			
			            System.out.println("Conexión cerrada.");
			
			        }
			
			    } catch (IOException e) {
			
			        System.err.println("Error al cerrar el cliente: " + e.getMessage());
			
			    }
			
			}
			
			}
		
}

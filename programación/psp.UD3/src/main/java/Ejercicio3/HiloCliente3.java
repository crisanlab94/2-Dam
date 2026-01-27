package Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCliente3 extends Thread {
    @Override
    public void run() {
    	     String host = "localhost"; 
			int puerto = 44444;     
			Socket cliente = null;
			
        try  {
        	 cliente = new Socket(host, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            
            // Leemos el número que nos asigna el servidor
            String mensaje = entrada.readLine();
            System.out.println("[Cliente] El servidor dice: " + mensaje);
            
           
            Thread.sleep(2000); 
            
        } catch (Exception e) {
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
    

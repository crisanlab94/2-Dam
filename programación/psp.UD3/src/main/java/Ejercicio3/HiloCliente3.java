package Ejercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCliente3 extends Thread {
    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 44444)) {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Leemos el número que nos asigna el servidor
            String mensaje = entrada.readLine();
            System.out.println("[Cliente] El servidor dice: " + mensaje);
            
           
            Thread.sleep(2000); 
            
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
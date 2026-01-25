package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor implements Runnable {
    private Socket cliente; 
    public HiloServidor(Socket cliente) { 
        this.cliente = cliente; 
    }
 
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

            String texto;
            while ((texto = entrada.readLine()) != null) {
                salida.println("ECO:" + texto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            
            System.out.println("[" + new java.util.Date() + "] Cliente desconectado.");
            try { 
            	cliente.close(); 
            	} catch (IOException e) { 
            		e.printStackTrace();
            	}
        }
    }

}

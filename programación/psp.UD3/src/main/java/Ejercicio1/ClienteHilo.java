package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ClienteHilo extends Thread {
	
	  @Override
	    public void run() { 
		  String host = "localhost"; 
			int puerto = 44444;     
			Socket cliente = null;
			 Scanner sc = new Scanner(System.in);
			 try {
		

	            System.out.println("Conectando al servidor..");

	            cliente = new Socket(host, puerto);

	            System.out.println("¡Conexión establecida!");
			
				BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
	            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
			
	            String mensaje = "";
	            while (mensaje != null && !mensaje.equals("fin")) {
	                System.out.print("Escribe tu mensaje: ");
	                mensaje = sc.nextLine();
	                salida.println(mensaje); 

	                if (!mensaje.equals("fin")) {
	                    String respuesta = entrada.readLine();

	            System.out.println(LocalDateTime.now() + " Cliente con id "+ this.getId() + " dice: " + mensaje);
	            System.out.println(respuesta);
	                }
	            }
			} catch (IOException e) {
			
			    System.err.println("Error de entrada/salida: " + e.getMessage());
			
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
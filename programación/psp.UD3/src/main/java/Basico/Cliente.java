package Basico;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

		public static void main(String[] args) {
			
		
			   // 1. Definir el host y el puerto del servidor

	        String host = "localhost"; // IP de la propia máquina

	        int puerto = 6000;

	        Socket cliente = null;

	        Scanner sc = new Scanner(System.in);

	        try {

	            // 2. Crear el socket y conectar al servidor

	            System.out.println("Conectando al servidor en " + host + " por el puerto " + puerto + "...");

	            cliente = new Socket(host, puerto);

	            System.out.println("¡Conexión establecida!");



	            // Configurar flujos para enviar y recibir datos
	            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
	            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

	            // Bucle para enviar mensajes
	            String mensaje = "";
	            while (mensaje != null && !mensaje.equals("fin")) {
	            System.out.print("Escribe tu mensaje: ");
	           
	            mensaje = sc.nextLine();
	            salida.println(mensaje);
	            
	            System.out.println("Cliente dice: " + mensaje);

	            }

	            // 5. Leer la respuesta del servidor

	            String respuesta = entrada.readLine();

	            System.out.println("Respuesta del servidor: " + respuesta);
	            

	        } catch (IOException e) {

	            System.err.println("Error de entrada/salida: " + e.getMessage());

	        } finally {

	            // 6. Cerrar el socket del cliente para liberar recursos

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




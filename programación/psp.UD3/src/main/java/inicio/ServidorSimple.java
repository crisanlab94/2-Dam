package inicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorSimple {
	public static void main(String[] args) {
		 int puerto = 6000;

	        ServerSocket servidor = null;

	        Socket clienteConectado = null;



	        try { 

	            // 2. Crear el ServerSocket para escuchar peticiones

	            servidor = new ServerSocket(puerto);

	            System.out.println("Servidor escuchando en el puerto " + puerto);



	            // 3. Esperar y aceptar una conexión

	            clienteConectado = servidor.accept();

	            System.out.println("Cliente conectado desde: " + clienteConectado.getInetAddress());



	            // 4. Configurar flujos de lectura y escritura

	            BufferedReader entrada = new BufferedReader(

	                    new InputStreamReader(clienteConectado.getInputStream()));

	            

	            PrintWriter salida = new PrintWriter(clienteConectado.getOutputStream(), true);



	            System.out.println("Conectado. Escribe tu mensaje:");

	            Scanner sc = new Scanner(System.in);



	            String mensaje = entrada.readLine();

	            System.out.print("> ");

	            System.out.println("Servidor: Cliente dice: " + mensaje.toLowerCase());

	 

	            salida.println("Servidor recibió: " + mensaje.toUpperCase());



	        } catch (IOException e) {

	            // Manejo de excepciones de entrada/salida o errores de red

	            System.err.println("Error en el servidor: " + e.getMessage());

	            e.printStackTrace();



	        } finally {

	            // 5. Cerrar recursos siempre para liberar el puerto

	            try {

	                if (clienteConectado != null)

	                    clienteConectado.close();

	                if (servidor != null)

	                    servidor.close();



	                System.out.println("Recursos cerrados y servidor finalizado.");



	            } catch (IOException e) {

	                System.err.println("Error al cerrar sockets: " + e.getMessage());

	            }

	        }

	    }

}

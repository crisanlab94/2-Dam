package inicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteSimple {
	public static void main(String[] args) {
		
	
	String host = "localhost"; //host servidor con el que el cliente quiere conectarse
	int puerto = 6000;//puerto remoto en el servidor que el cliente conoce         
	Socket cliente;
	try {
		cliente = new Socket(host, puerto);
		System.out.println("Cliente: conexión establecida");
		
		//Configurar flujos para enviar y recibir datos 
		
		BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
		
		String mensajeParaServidor = "Hola soy un cliente";
		
		//Enviar mensaje al servidor
		salida.println(mensajeParaServidor);
		
		
		
		
		/*
		//Informacion de prueba 
		InetAddress i= cliente.getInetAddress ();
		System.out.println("Cliente: Puerto local: "+ cliente.getLocalPort());
		System.out.println("Cliente: Puerto Remoto: "+ cliente.getPort());  
		 System.out.println("Cliente: Host Remoto: "+ i.getHostName().toString());  
		 System.out.println("Cliente: IP Host Remoto: "+ i.getHostAddress().toString());
		 */
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//conecta

	
	}

}

package Ejercicio0;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSimple {
public static void main(String[] args) {
	String Host = "localhost"; //host servidor con el que el cliente quiere conectarse
	int Puerto = 5555;//puerto remoto en el servidor que el cliente conoce         
	Socket cliente = null;
	try {
		cliente = new Socket(Host, Puerto);
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//conecta

	InetAddress i= cliente.getInetAddress ();
	System.out.println("Puerto local: "+ cliente.getLocalPort());
	System.out.println("Puerto Remoto: "+ cliente.getPort());  
	 System.out.println("Host Remoto: "+ i.getHostName().toString());  
	 System.out.println("IP Host Remoto: "+ i.getHostAddress().toString());

}

	
}


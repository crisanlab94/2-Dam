package Ejercicio0;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteHilo extends Thread{
	  @Override
	    public void run() { 
		  String Host = "localhost"; 
			int Puerto = 5555;     
			Socket cliente = null;
			try {
				cliente = new Socket(Host, Puerto);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			InetAddress i= cliente.getInetAddress ();
			System.out.println("Puerto local: "+ cliente.getLocalPort());
			System.out.println("Puerto Remoto: "+ cliente.getPort());  
			 System.out.println("Host Remoto: "+ i.getHostName().toString());  
			 System.out.println("IP Host Remoto: "+ i.getHostAddress().toString());

	  }
	

}

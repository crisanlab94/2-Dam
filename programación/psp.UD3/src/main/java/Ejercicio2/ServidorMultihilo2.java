package Ejercicio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultihilo2 {
	public static void main(String[] args) {

		int puerto = 44444;
		ServerSocket servidor = null;
		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciando...");

			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Conecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
				Thread hilo = new Thread(new HiloServidor2(cliente));
				hilo.start();

			}
 
		} catch (IOException e) {
			System.err.println("Error en el servidor: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
			} catch (IOException e) {
				System.err.println("Error cerrando el servidor: " + e.getMessage());
			}
		}
	}

}

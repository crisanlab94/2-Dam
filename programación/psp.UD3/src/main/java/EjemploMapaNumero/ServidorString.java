package EjemploMapaNumero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorString {

	public static void main(String[] args) {

		int puerto = 5555;
		ServerSocket servidor = null;
		
		// 1. CREAMOS EL MONITOR (El mapa) antes de empezar
		PuntuacionJuegoString monitor = new PuntuacionJuegoString();

		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor (STRING) escuchando en el puerto " + puerto);

			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());

				// 2. PASAMOS EL MONITOR AL HILO para que todos compartan el mapa
				Thread hilo = new Thread(new HiloServidorString(cliente, monitor));
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
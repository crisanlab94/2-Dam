package EjemploMapaNumero;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEntero {

	public static void main(String[] args) {

		int puerto = 5555;
		ServerSocket servidor = null;
		
		// 1. CREAMOS EL MONITOR (Mapa de enteros)
		PuntuacionJuegoEntero monitor = new PuntuacionJuegoEntero();

		try {
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor (INT) escuchando en el puerto " + puerto);

			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress());

				// 2. PASAMOS EL MONITOR INT AL HILO
				Thread hilo = new Thread(new HiloServidorEntero(cliente, monitor));
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

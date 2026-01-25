package Ejercicio1;

	import java.io.IOException;
	import java.net.ServerSocket;
	import java.net.Socket;
    import java.util.Date;

	public class ServidorMultihilo {

		public static void main(String[] args) {

			int puerto = 44444;
			ServerSocket servidor = null;
			try {
				servidor = new ServerSocket(puerto);
				System.out.println("Servidor escuchando en el puerto " + puerto);

				while (true) {
					Socket cliente = servidor.accept();
					Date fecha = new Date();
					System.out.println("[" + fecha + "] Nuevo cliente conectado: " + cliente.getInetAddress());
	 
					Thread hilo = new Thread(new HiloServidor(cliente));
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




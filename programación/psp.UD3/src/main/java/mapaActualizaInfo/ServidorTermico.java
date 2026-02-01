package mapaActualizaInfo;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTermico {
    public static void main(String[] args) {
        TermostatoMonitor monitor = new TermostatoMonitor();
        // 1. Declaramos el ServerSocket fuera del try
        ServerSocket servidor = null;

        try {
            // 2. Inicializamos en el puerto 4000
            servidor = new ServerSocket(4000);
            System.out.println("🔥 Servidor térmico activo en puerto 4000...");

            while (true) {
                // 3. Aceptamos la conexión
                Socket socketCliente = servidor.accept();
                
                // 4. Lanzamos el hilo pasándole el socket y el monitor
                Thread hilo = new Thread(new HiloTermico(socketCliente, monitor));
                hilo.start();
            }

        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            // 5. El bloque finally por si el servidor se detiene
            try {
                if (servidor != null) {
                    servidor.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
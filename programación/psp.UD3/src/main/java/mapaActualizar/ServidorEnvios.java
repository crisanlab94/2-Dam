package mapaActualizar;



import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEnvios {
    public static void main(String[] args) {
        EnviosMonitor monitor = new EnviosMonitor();
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(6000);
            System.out.println("🚚 Servidor de Logística (Paquetes) activo en puerto 6000...");

            while (true) {
                // Aceptamos la conexión del cliente
                Socket cliente = servidor.accept();
                
                // Creamos y lanzamos el hilo
                Thread hilo = new Thread(new HiloEnvio(cliente, monitor));
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierre manual del ServerSocket
            try {
                if (servidor != null) servidor.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
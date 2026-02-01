package mapaDiscoteca;


import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAforo {
    public static void main(String[] args) {
        // 1. Creamos el monitor (objeto compartido)
        AforoMonitor monitor = new AforoMonitor();
        
        // 2. Declaramos el ServerSocket fuera para que sea accesible en el finally
        ServerSocket servidor = null;

        try {
            // 3. Abrimos el puerto 5500
            servidor = new ServerSocket(5500);
            System.out.println("🔥 [SERVIDOR] Sistema de Aforo activo en puerto 5500...");

            while (true) {
                // 4. El servidor se detiene aquí hasta que alguien conecta
                Socket cliente = servidor.accept();
                
                // 5. Lanzamos el hilo para atender al sensor
                Thread hilo = new Thread(new HiloAforo(cliente, monitor));
                hilo.start();
            }

        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            // 6. Cierre manual por si el servidor se detuviera
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
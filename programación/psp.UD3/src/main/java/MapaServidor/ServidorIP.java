package MapaServidor;


import java.net.ServerSocket;
import java.net.Socket;

public class ServidorIP {
    public static void main(String[] args) {
        int puerto = 5555;
        
        // El Monitor se crea fuera del bucle: ¡Vital para que se comparta!
        AsignadorIP monitor = new AsignadorIP();

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println(">>> SERVIDOR DE ASIGNACIÓN DE IPs INICIADO <<<");
            System.out.println("Puerto: " + puerto);

            while (true) {
                // Esperamos a un cliente
                Socket cliente = servidor.accept();
                System.out.println("Nuevo cliente conectado desde: " + cliente.getInetAddress());

                // Lanzamos un hilo nuevo para cada petición
                Thread hilo = new Thread(new HiloServidorIP(cliente, monitor));
                hilo.start();
            }
        } catch (Exception e) {
            System.err.println("Error crítico en el servidor: " + e.getMessage());
        }
    }
}
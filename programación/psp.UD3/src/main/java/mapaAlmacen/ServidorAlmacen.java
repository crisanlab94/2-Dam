package mapaAlmacen;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorAlmacen {
    public static void main(String[] args) {
        int puerto = 6000;
        
        // IMPORTANTÍSIMO: El monitor se crea FUERA del bucle
        AlmacenMonitor monitor = new AlmacenMonitor();
        
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor de Almacén listo en puerto " + puerto);
            
            // Bucle infinito para aceptar clientes
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nuevo repartidor conectado desde: " + cliente.getInetAddress());
                
                // Lanzamos un hilo nuevo para cada cliente
                Thread hilo = new Thread(new HiloServidorAlmacen(cliente, monitor));
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Si el servidor se apagara, aquí generaríamos el informe
            // monitor.generarInforme();
        }
    }
}

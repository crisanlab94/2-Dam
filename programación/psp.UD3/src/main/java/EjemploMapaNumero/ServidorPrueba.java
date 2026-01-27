package EjemploMapaNumero;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPrueba {

    public static void main(String[] args) {
        int puerto = 5555;
        ServerSocket servidor = null;
        
        // 1. CREAMOS EL MONITOR (Modelo String para nombres)
        PuntuacionJuegoString monitor = new PuntuacionJuegoString();

        try {
            servidor = new ServerSocket(puerto);
            System.out.println(">>> SERVIDOR DE COMANDOS INICIADO <<<");
            System.out.println("Escuchando peticiones en el puerto " + puerto);

            while (true) {
                // ESPERAR AL CLIENTE
                Socket cliente = servidor.accept();
                System.out.println("Conexión entrante desde: " + cliente.getInetAddress());

                // 2. LANZAR EL HILO DE MÉTODOS (El que entiende SUMAR, BORRAR, etc.)
                Thread hilo = new Thread(new HiloServidorMetodos(cliente, monitor));
                hilo.start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            try {
                if (servidor != null) servidor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
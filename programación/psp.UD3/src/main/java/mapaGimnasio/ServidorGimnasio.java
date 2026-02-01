package mapaGimnasio;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorGimnasio {
    public static void main(String[] args) {
        GimnasioMonitor monitor = new GimnasioMonitor();
        try (ServerSocket servidor = new ServerSocket(5000)) {
            System.out.println("💪 Servidor SmartFit iniciado (Puerto 5000)...");
            while (true) {
                Socket s = servidor.accept();
                new Thread(new HiloGimnasio(s, monitor)).start();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
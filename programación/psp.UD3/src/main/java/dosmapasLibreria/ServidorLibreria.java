package dosmapasLibreria;



import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLibreria {
    public static void main(String[] args) {
        LibreriaMonitor monitor = new LibreriaMonitor();
        try (ServerSocket servidor = new ServerSocket(8000)) {
            System.out.println("📚 Servidor de Librería activo en puerto 8000...");
            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new HiloLibrero(cliente, monitor)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
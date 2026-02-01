package dosmapasbiblioteca;



import java.net.ServerSocket;
import java.net.Socket;

public class ServidorBiblioteca {
    public static void main(String[] args) {
        BibliotecaMonitor monitor = new BibliotecaMonitor();
        try (ServerSocket servidor = new ServerSocket(6000)) {
            System.out.println("🏢 Servidor Biblioteca Central activo (Puerto 6000)...");
            while (true) {
                Socket s = servidor.accept();
                new Thread(new HiloBiblioteca(s, monitor)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
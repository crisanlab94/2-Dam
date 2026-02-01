package mapaSobreescribeSuma;


import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLogistica {
    public static void main(String[] args) {
        CamionMonitor monitor = new CamionMonitor();
        try (ServerSocket servidor = new ServerSocket(7000)) {
            System.out.println("🚚 Central de Logística escuchando en puerto 7000...");
            while (true) {
                Socket s = servidor.accept();
                new Thread(new HiloCamion(s, monitor)).start();
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
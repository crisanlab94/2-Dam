package mapaSumar;


import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPuntos {
    public static void main(String[] args) {
        PuntosMonitor monitor = new PuntosMonitor();
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(5000);
            System.out.println("💰 Servidor de Fidelización activo (Puerto 5000)...");

            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new HiloPuntos(cliente, monitor)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servidor != null) servidor.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
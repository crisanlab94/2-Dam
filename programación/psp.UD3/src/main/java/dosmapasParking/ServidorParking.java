package dosmapasParking;



import java.net.ServerSocket;
import java.net.Socket;

public class ServidorParking {
    public static void main(String[] args) {
        ParkingMonitor monitor = new ParkingMonitor();
        try (ServerSocket servidor = new ServerSocket(9000)) {
            System.out.println("🅿️ Servidor de Parking iniciado en puerto 9000...");
            while (true) {
                Socket s = servidor.accept();
                new Thread(new HiloSensor(s, monitor)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

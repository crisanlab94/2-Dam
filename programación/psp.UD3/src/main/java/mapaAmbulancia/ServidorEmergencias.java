package mapaAmbulancia;



import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEmergencias {
    public static void main(String[] args) {
        int puerto = 7000;
        EmergenciasMonitor monitor = new EmergenciasMonitor();

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("🚑 Central de Emergencias escuchando en puerto " + puerto);

            while (true) {
                Socket ambulancia = servidor.accept();
                // Delegamos en un hilo para atender a la ambulancia
                Thread hilo = new Thread(new HiloAmbulancia(ambulancia, monitor));
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

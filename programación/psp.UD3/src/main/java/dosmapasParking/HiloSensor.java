package dosmapasParking;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloSensor implements Runnable {
    private Socket socket;
    private ParkingMonitor monitor;

    public HiloSensor(Socket socket, ParkingMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea = entrada.readLine(); // "MATRICULA,NOMBRE,MINUTOS"
            
            if (linea != null && !linea.isEmpty()) {
                String[] partes = linea.split(",");
                String matricula = partes[0].trim();
                String nombre = partes[1].trim();
                int minutos = Integer.parseInt(partes[2].trim());

                // Actualizamos el monitor compartido
                monitor.registrarSalida(matricula, nombre, minutos);
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package mapaGimnasio;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloGimnasio implements Runnable {
    private Socket socket;
    private GimnasioMonitor monitor;

    public HiloGimnasio(Socket socket, GimnasioMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea = in.readLine(); // Formato: "DNI,ACTIVIDAD,CALORIAS"
            if (linea != null) {
                String[] p = linea.split(",");
                monitor.registrarEntrenamiento(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()));
            }
            socket.close();
        } catch (Exception e) {
            System.err.println("Error en la recepción de datos.");
        }
    }
}
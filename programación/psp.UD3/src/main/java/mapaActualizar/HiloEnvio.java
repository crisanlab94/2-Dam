package mapaActualizar;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloEnvio implements Runnable {
    private Socket socket;
    private EnviosMonitor monitor;

    public HiloEnvio(Socket socket, EnviosMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String trama = in.readLine(); // "ID_PAQUETE,CIUDAD"

            if (trama != null) {
                String[] partes = trama.split(",");
                String id = partes[0].trim();
                String ciudad = partes[1].trim(); // Aquí no hay parseInt porque es String

                monitor.actualizarUbicacion(id, ciudad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierre manual obligatorio para tu profesora
            try {
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
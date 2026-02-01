package mapaSumar;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloPuntos implements Runnable {
    private Socket socket;
    private PuntosMonitor monitor;

    public HiloPuntos(Socket socket, PuntosMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String linea = in.readLine();
            
            if (linea != null) {
                String[] partes = linea.split(",");
                String dni = partes[0].trim();
                int puntos = Integer.parseInt(partes[1].trim());

                monitor.sumarPuntos(dni, puntos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

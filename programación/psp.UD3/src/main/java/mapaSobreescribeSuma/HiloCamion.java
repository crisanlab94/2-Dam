package mapaSobreescribeSuma;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloCamion implements Runnable {
    private Socket socket;
    private CamionMonitor monitor;

    public HiloCamion(Socket socket, CamionMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea = in.readLine(); // "ID,CONDUCTOR,KM"
            if (linea != null) {
                String[] p = linea.split(",");
                String id = p[0].trim();
                String conductor = p[1].trim();
                int km = Integer.parseInt(p[2].trim());

                monitor.actualizarCamion(id, conductor, km);
            }
            socket.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
}

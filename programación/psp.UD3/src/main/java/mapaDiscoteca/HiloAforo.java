package mapaDiscoteca;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloAforo implements Runnable {
    private Socket socket;
    private AforoMonitor monitor;

    public HiloAforo(Socket socket, AforoMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        // Consola Servidor: Quién se conecta
        System.out.println("🔌 Sensor conectado desde: " + socket.getInetAddress());
        
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String trama = in.readLine(); // "SALA,PERSONAS"
            
            if (trama != null) {
                String[] p = trama.split(",");
                monitor.registrarEntrada(p[0].trim(), Integer.parseInt(p[1].trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (socket != null) socket.close();
            } catch (Exception e2) { e2.printStackTrace(); }
        }
    }
}
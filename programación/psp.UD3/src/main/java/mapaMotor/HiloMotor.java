package mapaMotor;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloMotor implements Runnable {
    private Socket s;
    private MotoresMonitor m;

    public HiloMotor(Socket s, MotoresMonitor m) {
        this.s = s;
        this.m = m;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
            String linea = in.readLine(); // Llega: "101,85"
            if (linea != null) {
                String[] partes = linea.split(",");
                int serie = Integer.parseInt(partes[0].trim());
                int presion = Integer.parseInt(partes[1].trim());

                m.registrarPrueba(serie, presion);
            }
        } catch (Exception e) {
            System.err.println("Error en hilo motor");
        }
    }
}
package mapaMotor;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteMotor implements Runnable {
    private int serie, presion;

    public ClienteMotor(int serie, int presion) {
        this.serie = serie;
        this.presion = presion;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 7000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            // Enviamos los dos números como String separados por coma
            out.println(serie + "," + presion);
        } catch (Exception e) { /* ... */ }
    }
}
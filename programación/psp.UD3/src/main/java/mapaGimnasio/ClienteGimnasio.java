package mapaGimnasio;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteGimnasio implements Runnable {
    private String dni, actividad;
    private int calorias;

    public ClienteGimnasio(String dni, String actividad, int calorias) {
        this.dni = dni;
        this.actividad = actividad;
        this.calorias = calorias;
    }

    @Override
    public void run() {
        try (Socket s = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            out.println(dni + "," + actividad + "," + calorias);
        } catch (Exception e) {
            System.err.println("No se pudo conectar con el gimnasio.");
        }
    }
}

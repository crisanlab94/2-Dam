package mapaDiscoteca;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteAforo implements Runnable {
    private String sala;
    private int cantidad;

    public ClienteAforo(String sala, int cantidad) {
        this.sala = sala;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        Socket s = null;
        PrintWriter out = null;
        try {
            s = new Socket("localhost", 5500);
            out = new PrintWriter(s.getOutputStream(), true);
            
            out.println(sala + "," + cantidad);
            // Consola Lanzador: Confirmación
            System.out.println("🚀 [CLIENTE] Enviados " + cantidad + " a " + sala);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (s != null) s.close();
            } catch (Exception e2) { e2.printStackTrace(); }
        }
    }
}
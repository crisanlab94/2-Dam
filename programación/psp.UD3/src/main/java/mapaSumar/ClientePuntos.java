package mapaSumar;



import java.io.PrintWriter;
import java.net.Socket;

public class ClientePuntos implements Runnable {
    private String dni;
    private int puntos;

    public ClientePuntos(String dni, int puntos) {
        this.dni = dni;
        this.puntos = puntos;
    }

    @Override
    public void run() {
        Socket s = null;
        PrintWriter out = null;
        try {
            s = new Socket("localhost", 5000);
            out = new PrintWriter(s.getOutputStream(), true);
            out.println(dni + "," + puntos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (s != null) s.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
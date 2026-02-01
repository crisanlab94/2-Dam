package mapaActualizar;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteEnvios implements Runnable {
    private String idPaquete;
    private String ubicacion;

    public ClienteEnvios(String idPaquete, String ubicacion) {
        this.idPaquete = idPaquete;
        this.ubicacion = ubicacion;
    }

    @Override
    public void run() {
        Socket s = null;
        PrintWriter out = null;
        try {
            s = new Socket("localhost", 6000);
            out = new PrintWriter(s.getOutputStream(), true);
            
            // Enviamos la trama: "ID,CIUDAD"
            out.println(idPaquete + "," + ubicacion);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cierre manual estilo "Old School"
            try {
                if (out != null) out.close();
                if (s != null) s.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
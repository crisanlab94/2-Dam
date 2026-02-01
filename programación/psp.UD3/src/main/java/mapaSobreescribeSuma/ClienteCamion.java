package mapaSobreescribeSuma;


import java.io.PrintWriter;
import java.net.Socket;

public class ClienteCamion implements Runnable {
    private String id, conductor;
    private int km;

    public ClienteCamion(String id, String conductor, int km) {
        this.id = id;
        this.conductor = conductor;
        this.km = km;
    }

    @Override
    public void run() {
        try (Socket s = new Socket("localhost", 7000);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            out.println(id + "," + conductor + "," + km);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
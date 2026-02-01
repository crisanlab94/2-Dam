package mapaAmbulancia;


import java.io.PrintWriter;
import java.net.Socket;

public class LanzadorExamen {
    public static void main(String[] args) {
        // Simulamos 5 ambulancias reportando datos
        enviarDatos(1, "CRITICA", "false"); // Paciente 1 en sitio
        enviarDatos(2, "LEVE", "true");    // Paciente 2 de camino
        enviarDatos(3, "GRAVE", "true");
        enviarDatos(1, "CRITICA", "true");  // Paciente 1 ahora de camino
        enviarDatos(4, "MODERADA", "false");
    }

    private static void enviarDatos(int id, String prio, String traslado) {
        new Thread(() -> {
            try (Socket s = new Socket("localhost", 7000);
                 PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
                out.println(id + "," + prio + "," + traslado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
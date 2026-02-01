package mapaAmbulancia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloAmbulancia implements Runnable {
    private Socket socket;
    private EmergenciasMonitor monitor;

    public HiloAmbulancia(Socket socket, EmergenciasMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String linea = entrada.readLine(); // "ID,PRIORIDAD,BOOLEAN"
            
            if (linea != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0].trim());
                Prioridad p = Prioridad.valueOf(partes[1].trim().toUpperCase());
                boolean traslado = Boolean.parseBoolean(partes[2].trim());

                monitor.registrarEmergencia(id, p, traslado);
            }
            socket.close();
        } catch (Exception e) {
            System.err.println("Error procesando ambulancia: " + e.getMessage());
        }
    }
}
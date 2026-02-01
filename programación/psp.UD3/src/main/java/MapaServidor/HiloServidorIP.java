package MapaServidor;



import java.io.*;
import java.net.Socket;

public class HiloServidorIP implements Runnable {
    private Socket socket;
    private AsignadorIP monitor;

    public HiloServidorIP(Socket socket, AsignadorIP monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            // Formato esperado: "ACCION,ID,IP" (ej: "ASIGNAR,101,192.168.1.5")
            String linea = entrada.readLine();
            if (linea != null) {
                String[] partes = linea.split(",");
                String accion = partes[0].toUpperCase();
                int id = Integer.parseInt(partes[1]);

                if (accion.equals("ASIGNAR")) {
                    String ip = partes[2];
                    monitor.asignarIP(id, ip);
                    salida.println("OK: IP " + ip + " asignada al ID " + id);
                } else if (accion.equals("CONSULTAR")) {
                    String ipAsignada = monitor.obtenerIP(id);
                    salida.println("CONSULTA: El ID " + id + " tiene la IP: " + ipAsignada);
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
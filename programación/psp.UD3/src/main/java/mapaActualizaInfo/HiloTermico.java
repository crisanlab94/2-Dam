package mapaActualizaInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class HiloTermico implements Runnable {
    private Socket socket;
    private TermostatoMonitor monitor;

    public HiloTermico(Socket socket, TermostatoMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        // 1. Declaramos el lector fuera del bloque try
        BufferedReader in = null;

        try {
            // 2. Inicializamos el flujo de lectura
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String trama = in.readLine(); // "ID,TEMP"
            
            if (trama != null) {
                String[] p = trama.split(",");
                String id = p[0].trim();
                int temp = Integer.parseInt(p[1].trim());

                monitor.actualizarTemperatura(id, temp);
            }

        } catch (Exception e) {
            System.err.println("Error al leer datos del sensor.");
        } finally {
            // 3. El bloque finally asegura que cerramos todo pase lo que pase
            try {
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
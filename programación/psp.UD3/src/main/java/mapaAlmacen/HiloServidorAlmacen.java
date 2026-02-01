package mapaAlmacen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidorAlmacen implements Runnable {
    private Socket socket;
    private AlmacenMonitor monitor;

    public HiloServidorAlmacen(Socket socket, AlmacenMonitor monitor) {
        this.socket = socket;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        try {
            // 1. Canales de comunicación
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // 2. Esperamos el mensaje del cliente (Ejemplo: "101,ENTREGADO,true")
            String linea = entrada.readLine();

            if (linea != null && !linea.isEmpty()) {
                // 3. Troceamos la información
                String[] partes = linea.split(",");
                
                int id = Integer.parseInt(partes[0].trim());
                
                // --- TRUCO PARA EL ENUM ---
                // Convertimos el String a Enum. Usamos toUpperCase por si el cliente escribe "entregado"
                Estado nuevoEstado = Estado.valueOf(partes[1].trim().toUpperCase());
                
                // --- TRUCO PARA EL BOOLEAN ---
                boolean esUrgente = Boolean.parseBoolean(partes[2].trim());

                // 4. Actualizamos el Monitor (Objeto compartido)
                monitor.actualizarPedido(id, nuevoEstado, esUrgente);

                // 5. Respondemos al cliente
                salida.println("OK: Pedido " + id + " registrado como " + nuevoEstado);
            }

            socket.close(); // Siempre cerrar al terminar el hilo

        } catch (Exception e) {
            System.err.println("Error en el hilo: " + e.getMessage());
        }
    }
}

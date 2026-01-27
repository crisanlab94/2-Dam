package EjemploMapaNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidorMetodos implements Runnable {
	   private Socket socket;
	    private PuntuacionJuegoString compartido; // El objeto con el Mapa

	    public HiloServidorMetodos(Socket socket, PuntuacionJuegoString compartido) {
	        this.socket = socket;
	        this.compartido = compartido;
	    }
	
	@Override
	public void run() {
	    try {
	        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

	        String linea = entrada.readLine(); // El cliente envía: "ACCION,NOMBRE,PUNTOS"
	        
	        if (linea != null && !linea.isEmpty()) {
	            String[] partes = linea.split(",");
	            String comando = partes[0].toUpperCase(); // Convertimos a mayúsculas para evitar errores

	            // --- AQUÍ VA LA LÓGICA DE COMANDOS ---
	            
	            if (comando.equals("SUMAR")) {
	                // Formato: SUMAR,Ana,10
	                int total = compartido.sumarPuntos(partes[1], Integer.parseInt(partes[2]));
	                salida.println("Puntos sumados. Total de " + partes[1] + ": " + total);

	            } else if (comando.equals("BORRAR")) {
	                // Formato: BORRAR,Juan
	                compartido.eliminarJugador(partes[1]);
	                salida.println("Jugador " + partes[1] + " eliminado.");

	            } else if (comando.equals("GANADOR")) {
	                // Formato: GANADOR
	                String mejor = compartido.obtenerGanador();
	                salida.println("El líder es: " + mejor);

	            } else if (comando.equals("STATS")) {
	                // Formato: STATS
	                String info = compartido.obtenerEstadisticas();
	                salida.println("Estado del servidor: " + info);

	            } else if (comando.equals("RESET")) {
	                // Formato: RESET
	                compartido.vaciarRanking();
	                salida.println("Ranking vaciado por completo.");
	            
	        } else if (comando.equals("PARES")) {
	            salida.println(compartido.obtenerPares());

	        } else if (comando.equals("IMPARES")) {
	            salida.println(compartido.obtenerImpares());

	        } else if (comando.equals("MEDIA")) {
	            salida.println("La media de puntos es: " + compartido.obtenerMedia());
	        }
	        }
	        socket.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}

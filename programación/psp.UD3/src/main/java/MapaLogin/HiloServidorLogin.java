package MapaLogin;
import java.io.*;
import java.net.Socket;


	public class HiloServidorLogin implements Runnable {
	    private Socket socket;
	    private Monitor monitor;
	    private final String PASS_CORRECTA = "1234"; // Contraseña de ejemplo

	    public HiloServidorLogin(Socket socket, Monitor monitor) {
	        this.socket = socket;
	        this.monitor = monitor;
	    }

	    @Override
	    public void run() {
	        try {
	            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

	            // Formato esperado: "usuario,password"
	            String linea = entrada.readLine();
	            if (linea != null) {
	                String[] partes = linea.split(",");
	                String user = partes[0];
	                String pass = partes[1];

	                if (monitor.estaBloqueado(user)) {
	                    salida.println("ERROR: Usuario bloqueado. Demasiados intentos.");
	                } else {
	                    if (pass.equals(PASS_CORRECTA)) {
	                        monitor.resetearIntentos(user);
	                        salida.println("OK: Bienvenido al sistema.");
	                    } else {
	                        int actuales = monitor.registrarFallo(user);
	                        salida.println("ERROR: Password incorrecta. Intento " + actuales + "/3");
	                    }
	                }
	            }
	            socket.close();
	        } catch (Exception e) { e.printStackTrace(); }
	    }
	}



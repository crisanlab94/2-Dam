package MapaLogin;
	import java.net.ServerSocket;
	import java.net.Socket;

	public class ServidorLogin {
	    public static void main(String[] args) {
	        Monitor monitor = new Monitor();
	        try {
	            ServerSocket servidor = new ServerSocket(5555);
	            System.out.println("Servidor de Autenticación iniciado...");
	            while (true) {
	                Socket cliente = servidor.accept();
	                new Thread(new HiloServidorLogin(cliente, monitor)).start();
	            }
	        } catch (Exception e) { e.printStackTrace(); }
	    }
	}



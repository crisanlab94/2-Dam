package dosmapasbiblioteca;



import java.io.PrintWriter;
import java.net.Socket;

public class ClienteBiblioteca implements Runnable {
    private String dni; 
    private String titulo;
    private String nombre;
    private int dias;

   
    public ClienteBiblioteca(String dni, String titulo, String nombre, int dias) {
		super();
		this.dni = dni;
		this.titulo = titulo;
		this.nombre = nombre;
		this.dias = dias;
	}


	@Override
    public void run() {
        try (Socket s = new Socket("localhost", 6000);
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {
            
            // Enviamos el nombre también en la trama
            String trama = dni + "," + nombre + "," + titulo + "," + dias;
            out.println(trama);
            
        } catch (Exception e) {
            System.err.println("Error en cliente");
        }
    }
}
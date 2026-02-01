package MapaLogin;
import java.io.*;
import java.net.Socket;

	public class ClienteLogin implements Runnable {
	    private String user;
	    private String pass;

	    public ClienteLogin(String user, String pass) {
	        this.user = user;
	        this.pass = pass;
	    }

	    @Override
	    public void run() {
	        try {
	            Socket s = new Socket("localhost", 5555);
	            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

	            out.println(user + "," + pass);
	            System.out.println("LOGIN [" + user + "]: " + in.readLine());
	            
	            s.close();
	        } catch (Exception e) { e.printStackTrace(); }
	    }
	}



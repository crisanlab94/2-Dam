package boletin1;

import java.io.IOException;

public class EjemploProcessBuilder {
	public static void main(String[] args) {
		String [] comando = {"C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
	    try {
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

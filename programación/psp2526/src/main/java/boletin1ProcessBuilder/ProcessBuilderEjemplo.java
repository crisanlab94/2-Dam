package boletin1ProcessBuilder;

import java.io.IOException;

public class ProcessBuilderEjemplo {

	public static void main(String[] args) {
		String[] google = { "C:\\Program Files\\Google\\Chrome\\Application\\Chrome.exe", "moodle.org"};
		ProcessBuilder pb = new ProcessBuilder(google);
		try {
			Process p1 = pb.start();
			Process p2 = pb.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

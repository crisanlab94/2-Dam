package EjecutaJar;
import java.io.IOException;

public class LanzadorJar {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		System.out.println("Pepe");

		LanzadorJar a = new LanzadorJar();

		a.ejecutarJar("target/PspDam-0.0.1-SNAPSHOT.jar");

	}

	public void ejecutarJar(String rutaJar) { 

		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar", rutaJar);
			pb.redirectErrorStream(true);
            pb.inheritIO();
            pb.start();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}

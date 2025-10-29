package Tema1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class LanzadorPython {
	private final static String directorioGenerarClasses = "src/main/resources";
	private final static String rutaSource = "src\\main\\java";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Lanzador python");

		LanzadorPython a = new LanzadorPython();
		try {  
			a.crearPython("pepe.py", "print('Hello World')");
			a.ejecutarJar("target/PspDam-0.0.1-SNAPSHOT.jar");
		} catch (InterruptedException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crearPython(String nombreArchivo, String texto) throws InterruptedException {
		try {
			File archivoPython = new File(directorioGenerarClasses, nombreArchivo);
			archivoPython.getParentFile().mkdirs(); // Asegura que la carpeta exista
			FileWriter escribir = new FileWriter(archivoPython);
			escribir.write(texto); 
			escribir.close();  
 
			// Ejecutar el archivo Python 
			ProcessBuilder pb = new ProcessBuilder("python", archivoPython.getAbsolutePath());
			
			pb.redirectErrorStream(true);
			pb.inheritIO(); // Muestra salida directamente en consola
			
			pb.start().waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void ejecutarJar(String ruta) { 
		ProcessBuilder pb = new ProcessBuilder("-jar ", ruta);
		pb.redirectErrorStream(true);
		pb.inheritIO(); // Muestra salida directamente en consola
	}
}
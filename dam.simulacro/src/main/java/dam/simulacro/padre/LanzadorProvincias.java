package dam.simulacro.padre;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;



public class LanzadorProvincias {
	private static final String rutaResources = "src/main/resources/";
	private final static String directorioGenerarClasses = "target";
	private final static String rutaSource = "src/main/java/";
	
	public static void main(String[] args) {

		String[] provincias = {"Sevilla", "Huelva", "Jaén", "Granada", "Almería", "Córdoba","Málaga","Cádiz"};
		LanzadorProvincias lp = new LanzadorProvincias(); 
		
		 // Compila la clase Provincia antes de lanzar los procesos
		lp.compilaClase("dam/simulacro/Hijo/Provincia.java");
		
	     // Lanza un proceso por cada provincia
		Process[] procesoslp = lp.lanzarProcesos(provincias, lp);
		
        // Espera a que todos los procesos terminen
		int[] salidas = lp.esperarProcesos(procesoslp);
	}
		
		public void muestraContenidoFich(String rutaYNombre) throws FileNotFoundException {
			Scanner in = null;
			try {
				FileReader fichero = new FileReader(rutaYNombre);
				in = new Scanner(fichero);
				while (in.hasNext()) {
					System.out.println(in.next() + " "); // lectura palabra a palabra
				}
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}

		public Process[] lanzarProcesos(String [] provincias, LanzadorProvincias lp) {
			Process[] procesos = new Process[provincias.length];
			for (int i = 0; i < provincias.length; i++) {
				procesos[i] = lp.ejecutaProceso(provincias[i]);
			}
			return procesos;
		}

		public int[] esperarProcesos(Process[] procesos) {
			int[] salidas = new int[procesos.length];
			for (int i = 0; i < procesos.length; i++) {
				try {
					salidas[i] = procesos[i].waitFor();
				} catch (InterruptedException e) {
					System.err.println("Error esperando proceso " + i);
					e.printStackTrace(); 
				}
			}
			return salidas;
		}

		public void compilaClase(String ruta) {
			// Compilar el archivo fuente y generar el .class en el directorio target
			String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSource + ruta };
			ProcessBuilder pb = new ProcessBuilder(comando);
			try {
				pb.redirectErrorStream(true);
				pb.inheritIO();
				int exit = pb.start().waitFor();
				System.out.println(exit);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public Process ejecutaProceso(String provincia) {
			Process proceso = null;
			String[] comando = { "java", "-cp", "target/classes", "dam.simulacro.Hijo.Provincia",provincia};
					
			ProcessBuilder pb = new ProcessBuilder(comando);

			try {
				proceso = pb.start();
				int exit = proceso.waitFor();
				//System.out.println("Proceso terminado con código: " + exit);

				if (exit != 0) {
					BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
					String errorLinea;
					while ((errorLinea = errorReader.readLine()) != null) {
						//System.err.println("Error Padre: " + errorLinea);
					}
				} else {
					BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
					String linea;
					while ((linea = reader.readLine()) != null) {
						//System.out.println("Padre: " + linea);
						System.out.println(provincia+ " : " + linea);
					}
				}

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

			return proceso;
		}


}

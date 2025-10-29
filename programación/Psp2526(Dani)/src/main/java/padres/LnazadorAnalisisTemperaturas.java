package padres;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LnazadorAnalisisTemperaturas {
	private static final String rutaResources = "src/main/resources/";
	private final static String directorioGenerarClasses = "target";
	private final static String rutaSource = "src/main/java/";

	public static void main(String[] args) {

		int[] umbrales = { 20, 5, 1, 40, 30, 35, 41,8,0 };
		LnazadorAnalisisTemperaturas pepe = new LnazadorAnalisisTemperaturas(); // tu clase que ejecuta procesos
		Process[] procesosPepe = pepe.lanzarProcesos(umbrales, pepe);
		int[] salidas = pepe.esperarProcesos(procesosPepe);

		// hacer funcionLeeFichero
		pepe.muestraTodosLosFicheros(umbrales);

		/*
		 * LnazadorAnalisisTemperaturas pepe = new LnazadorAnalisisTemperaturas();
		 * pepe.compilaClase("hijos/AnalizadorTemperatura.java"); int[] umbrales = {
		 * 20,5, 1, 40, 30, 35,41}; int[] salidas = new int[umbrales.length];
		 * 
		 * Process[] procesosPepe = lanzarProcesos(umbrales, pepe); int[] salidas =
		 * esperarProcesos(procesosPepe);
		 * 
		 */

		/*
		 * Process[] procesos = new Process[umbrales.length]; for (int i = 0; i <
		 * umbrales.length; i++) { procesos[i] = pepe.ejecutaProceso(umbrales[i]); } for
		 * (int i = 0; i < umbrales.length; i++) { try {
		 * salidas[i]=procesos[i].waitFor(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}

	// TODO Auto-generated method stub

	public void muestraTodosLosFicheros(int[] umbrales) {
		System.out.println("Resultados:");
		for (int tempeUmbral : umbrales) {
			String rutaFIchero = rutaResources + tempeUmbral + ".txt";

			System.out.println("Umbral " + tempeUmbral + ":");
			try {
				muestraContenidoFich(rutaFIchero);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void muestraContenidoFich(String rutaYNombre) throws FileNotFoundException {
		Scanner in = null;
		try {
			FileReader fichero = new FileReader(rutaYNombre);
			in = new Scanner(fichero);
			while (in.hasNext()) {
				System.out.println(in.next() + " → dias"); // lectura palabra a palabra
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public Process[] lanzarProcesos(int[] umbrales, LnazadorAnalisisTemperaturas pepe) {
		Process[] procesos = new Process[umbrales.length];
		for (int i = 0; i < umbrales.length; i++) {
			procesos[i] = pepe.ejecutaProceso(umbrales[i]);
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

	public Process ejecutaProcesoGPT(int umbral) {
		Process proceso = null;
		String[] comando = { "java", "-cp", "target/classes", "hijos.AnalizadorTemperatura",
				"src/main/resources/datos.txt", String.valueOf(umbral), "src/main/resources/" + umbral + ".txt" };

		ProcessBuilder pb = new ProcessBuilder(comando);

		try {
			proceso = pb.start();
			int exit = proceso.waitFor();
			System.out.println("Proceso terminado con código: " + exit);

			if (exit != 0) {
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
				String errorLinea;
				while ((errorLinea = errorReader.readLine()) != null) {
					System.err.println("Error Padre: " + errorLinea);
				}
			} else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				String linea;
				while ((linea = reader.readLine()) != null) {
					System.out.println("Padre: " + linea);
				}
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return proceso;
	}

	/*
	 * public Process ejecutaProcesoCompila() { // Ejecutar la clase compilada
	 * usando su nombre de clase, no la ruta al .java //doy dos argumentos hola y
	 * adios Process proceso = null; String[] comando = { "java", "-cp",
	 * directorioGenerarClasses, "hijos.AnalizadorTemperatura","" }; ProcessBuilder
	 * pb = new ProcessBuilder(comando); try { //pb.redirectErrorStream(true);
	 * //pb.inheritIO(); Asi leo el padre. proceso= pb.start(); InputStream
	 * salidaHijoStream = proceso.getErrorStream(); BufferedReader reader = new
	 * BufferedReader(new InputStreamReader(proceso.getInputStream())); String linea
	 * = reader.readLine();
	 * 
	 * while (linea!=null) { System.out.println("padre:" + linea); linea
	 * =reader.readLine();
	 * 
	 * }
	 * 
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } return proceso; }
	 */

	public Process ejecutaProceso(int umbral) {
		Process proceso = null;
		// Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java
		// doy dos argumentos hola y adios
		String[] comando = { "java", "-cp", directorioGenerarClasses, "hijos.AnalizadorTemperatura",
				"src/main/resources/datos.txt", String.valueOf(umbral),
				"src/main/resources/" + String.valueOf(umbral) + ".txt" };

		ProcessBuilder pb = new ProcessBuilder(comando);
		try {
			// pb.redirectErrorStream(true);
			// pb.inheritIO();
			proceso = pb.start();
			int exit = proceso.waitFor();
			System.out.println(exit);

			if (exit != 0) {
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
				String errorLinea = errorReader.readLine();
				while (errorLinea != null) {
					System.err.println("Error Padre: " + errorLinea);
					errorLinea = errorReader.readLine();
				}
			} else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				String linea = reader.readLine();

				while (linea != null) {
					System.out.println("Padre: " + linea);
					linea = reader.readLine();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return proceso;
	}

	public Process ejecutarClasePlantilla(String className, String classpath, String... args) {
		Process proceso = null;

		List<String> comando = new ArrayList<String>();
		comando.add("java");
		comando.add("-cp");
		comando.add(classpath);
		comando.add(className);
		comando.addAll(Arrays.asList(args));

		ProcessBuilder pb = new ProcessBuilder(comando);

		try {
			proceso = pb.start();
			int exitCode = proceso.waitFor();
			System.out.println("Código de salida: " + exitCode);

			if (exitCode != 0) {
				BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
				String lineaError;
				while ((lineaError = errorReader.readLine()) != null) {
					System.err.println("Error: " + lineaError);
				}
			} else {
				BufferedReader outputReader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
				String lineaSalida;
				while ((lineaSalida = outputReader.readLine()) != null) {
					System.out.println("Salida: " + lineaSalida);
				}
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return proceso;
	}

}

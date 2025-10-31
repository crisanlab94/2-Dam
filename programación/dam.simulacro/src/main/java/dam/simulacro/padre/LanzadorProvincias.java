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
		
	     /* Lanza un proceso por cada provincia
		  //anterior para lanzar proceso por provincia
		   Process[] procesoslp = lp.lanzarProcesos(provincias, lp);
		   */
		
		 /* Lanzar los procesos hijos(antiguos)
        for (int i = 0; i < provincias.length; i++) {
            pedidosPorProvincia[i] = lp.ejecutaProceso(provincias[i]);
            totalPedidos += pedidosPorProvincia[i];
        }
        
        // Mostrar los resultados
        System.out.println("Nº total de Pedidos : " + totalPedidos);
        for (int i = 0; i < provincias.length; i++) {
            System.out.println(provincias[i] + ": " + pedidosPorProvincia[i]);
        }
        System.out.println("Nº total de Pedidos : " + totalPedidos);
    } */
		
        // Espera a que todos los procesos terminen
		//anterior
	   //int[] salidas = lp.esperarProcesos(procesoslp);
		
		 lp.lanzarProcesosYMostrarTotales(provincias);
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

		/*public Process[] lanzarProcesos(String [] provincias, LanzadorProvincias lp) {
			Process[] procesos = new Process[provincias.length];
			for (int i = 0; i < provincias.length; i++) {
				//procesos[i] = lp.ejecutaProceso(provincias[i]);
				procesos[i] = lp.lanzarProcesosYMostrarTotales(provincias[i]);
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
*/
		public void compilaClase(String ruta) {
			// Compilar el archivo fuente y generar el .class en el directorio target
			String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSource + ruta };
			ProcessBuilder pb = new ProcessBuilder(comando);
			try {
				pb.redirectErrorStream(true);
				pb.inheritIO();
				int exit = pb.start().waitFor();
				 if (exit != 0) {
		                System.err.println("Error compilando la clase Provincia");
		            }
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/*public Process ejecutaProceso(String provincia) {
			Process proceso = null;
			String[] comando = { "java", "-cp", "target/classes", "dam.simulacro.Hijo.Provincia",provincia};
					
			ProcessBuilder pb = new ProcessBuilder(comando);
			int numPedidos=0;

			try {
				proceso = pb.start();
				//Espera que termine
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
		*/
		
		public void lanzarProcesosYMostrarTotales(String[] provincias) {
		    int totalPedidos = 0;
		 
		    // Primero recorremos todas las provincias para calcular el total
		    for (String provincia : provincias) {
		        String[] comando = { "java", "-cp", "target/classes", "dam.simulacro.Hijo.Provincia", provincia };
		        ProcessBuilder pb = new ProcessBuilder(comando);

		        try {
		            Process proceso = pb.start();

		            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		            
		            String linea;
		            String ultimaLinea = null;

		            // Leer TODA la salida del hijo (por si imprime más de una línea)
		            while ((linea = reader.readLine()) != null) {
		                ultimaLinea = linea.trim();
		            }
		            // Imprime solo 1 linea
		            //String linea = reader.readLine(); // El hijo imprime el número de pedidos
		            reader.close();

		            if (ultimaLinea != null && !ultimaLinea.isEmpty()) {
		            //Muestra cada provincia y su número
		            System.out.println(ultimaLinea); 

		            // sumamos solo el número final (sin mostrarlo aparte)
	                String soloNumero = ultimaLinea.replaceAll("[^0-9]", "").trim();
	                if (!soloNumero.isEmpty()) {
	                    totalPedidos += Integer.parseInt(soloNumero);
	                }
	            } else {
	                System.out.println(provincia + ": (sin datos)");
	            }


		            proceso.waitFor();

		        } catch (IOException | InterruptedException e) {
		            e.printStackTrace();
		        }
		    }

		    // Mostrar el total  arriba
		    System.out.println("Nº total de Pedidos : " + totalPedidos);
		}



		
}

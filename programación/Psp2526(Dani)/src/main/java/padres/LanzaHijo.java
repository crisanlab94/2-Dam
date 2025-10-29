package padres;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LanzaHijo {
	private static final String rutaResources = "src/main/resources/";
    private final static String directorioGenerarClasses = "target";
    private final static String rutaSource = "src/main/java/";
	public static void main(String[] args) {
		LanzaHijo pepe = new LanzaHijo();
		pepe.compilaClase("hijos/SysoNum.java");
		pepe.ejecutaProcesoCompila22();		 
		 
		System.out.println();
		

		// TODO Auto-generated method stub

	}


	    public void compilaClase(String ruta) {
	        // Compilar el archivo fuente y generar el .class en el directorio target
	        String[] comando = {
	            "javac",
	            "-d", directorioGenerarClasses,
	            rutaSource + ruta 
	        };
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
	    
	     
	    public void ejecutaProcesoCompila() { 
	        // Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java
	    	//doy dos argumentos hola y adios 
	    	String[] comando = { "java", "-cp", directorioGenerarClasses, "hijos.SysoNum" };
	        ProcessBuilder pb = new ProcessBuilder(comando);
	        try { 
	            //pb.redirectErrorStream(true); 
	            //pb.inheritIO(); Asi leo el padre. 
	            Process proceso = pb.start();
		        InputStream salidaHijoStream = proceso.getErrorStream(); 
		        BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		        String linea = reader.readLine();
	        
		        while (linea!=null) {
		        	System.out.println("padre:" + linea);
		        	linea =reader.readLine();
					
				}
 

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }  
	    
	    
	    public void ejecutaProcesoCompila22() { 
	        // Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java
	    	//doy dos argumentos hola y adios 
	    	String[] comando = { "java", "-cp", directorioGenerarClasses, "hijos.SysoNum" };
	        ProcessBuilder pb = new ProcessBuilder(comando); 
	        try {
				//pb.redirectErrorStream(true); 
				//pb.inheritIO();
				Process p1 = pb.start();


				int exit = p1.waitFor();
				System.out.println(exit);

				if (exit != 0) {
					BufferedReader errorReader = new BufferedReader(new InputStreamReader(p1.getErrorStream()));
					String errorLinea = errorReader.readLine();
					while (errorLinea != null) {
						System.err.println("Error Padre: " + errorLinea);
						errorLinea = errorReader.readLine();
					}
				} else {
					BufferedReader reader = new BufferedReader(new InputStreamReader(p1.getInputStream()));
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
		}
	    } 



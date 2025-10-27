package Padre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzaHijo {
	private static final String rutaResources = "src/main/resources/";

    private final static String directorioGenerarClasses = "target";

    private final static String rutaSource = "src/main/java/";

	public static void main(String[] args) {

		LanzaHijo pepe = new LanzaHijo();

		pepe.compilaClase("hijos/SysoNum.java");

		pepe.ejecutaProcesoCompila();		 



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

	            pb.start().waitFor();

	        } catch (IOException e) {

	            e.printStackTrace();

	        } catch (InterruptedException e) {

	            e.printStackTrace();

	        }

	    }

	    

	     

	    public void ejecutaProcesoCompila() { 

	        // Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java

	    	//doy dos argumentos hola y adios 

	    	String[] comando = { "java", "-cp", directorioGenerarClasses, "hijos.SysoNum", "hola", "adios" };

	        ProcessBuilder pb = new ProcessBuilder(comando);

	        

	        try {

	            pb.redirectErrorStream(true);

	            pb.inheritIO(); 

	            Process proceso = pb.start();
	            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
	            
	        } catch (IOException e) {

	            e.printStackTrace();

	        }

	    } 



}
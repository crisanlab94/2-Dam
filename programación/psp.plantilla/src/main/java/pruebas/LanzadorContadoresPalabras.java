package pruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanzadorContadoresPalabras {
	  private static final String RUTA_SOURCE = "src/main/java/";
	    private static final String RUTA_CLASSES = "target/classes";
	    private static final String DIRECTORIO_GENERAR_CLASSES = "target";
	
	    public static void main(String[] args) {
	    	 LanzadorContadoresPalabras lanzador = new LanzadorContadoresPalabras();
	    	 String[] palabras = {"servicio", "hilo", "proceso","multihilo","concurrencia"};
	    	 lanzador.compilaClase("pruebas/ContadorPalabras.java");
	    	 lanzador.ejecuta(args);
	}
	    
	    public void compilaClase(String rutaRelativa) {
	        String[] comando = {"javac", "-d", DIRECTORIO_GENERAR_CLASSES, RUTA_SOURCE + rutaRelativa};
	        try {
	            ProcessBuilder pb = new ProcessBuilder(comando);
	            pb.redirectErrorStream(true);
	            pb.inheritIO(); // muestra errores en consola del padre
	            int exit = pb.start().waitFor();

	            if (exit == 0)
	                System.out.println("Compilación correcta de " + rutaRelativa);
	            else
	                System.err.println("Error compilando " + rutaRelativa);

	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	        
	        public void ejecuta(String[] argsString) {
	      
	            for (String arg : argsString) {
	                String[] comando = {"java", "-cp", RUTA_CLASSES, "psp.plantilla.pruebas", arg};

	                try {
	                    ProcessBuilder pb = new ProcessBuilder(comando);
	                    Process proceso = pb.start();

	                    // Lectura de la salida del hijo
	                    BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
	                    // Lectura de errores del hijo
	                    BufferedReader errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));

	                    String linea, ultima = null;
	                    while ((linea = salida.readLine()) != null) {
	                        ultima = linea.trim();
	                        System.out.println("Salida hijo → " + ultima);
	                    }

	                    while ((linea = errores.readLine()) != null) {
	                        System.err.println("Error hijo → " + linea);
	                    }

	                    proceso.waitFor();
	                    salida.close();
	                    errores.close();

	                } catch (IOException | InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	           
	    }

}

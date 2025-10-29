package Tema1;

import java.io.IOException;


public class LanzaSuma {
    private final static String directorioGenerarClasses = "target";
    private final static String rutaSource = "src\\main\\java";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LanzaSuma a = new LanzaSuma();
		a.ejecutaClase("");
		a.compilaClase(null);
	} 
	 
	  public void ejecutaClase(String ruta) {
	        // Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java
	    	//doy dos argumentos hola y adios
	        String[] comando = { "java", "-cp", directorioGenerarClasses, ruta,"False","5" };
	        ProcessBuilder pb = new ProcessBuilder(comando);
	        try { 
	            pb.redirectErrorStream(true);
	            pb.inheritIO();
	            pb.start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  
	    public void compilaClase(String ruta) {
	        // Compilar el archivo fuente y generar el .class en el directorio target
	        String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSource + ruta };
	        ProcessBuilder pb = new ProcessBuilder(comando);
	        try {
	            pb.redirectErrorStream(true);
	            pb.inheritIO();
	            pb.start();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}

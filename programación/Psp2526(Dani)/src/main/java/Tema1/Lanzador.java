package Tema1;

import java.io.IOException;

public class Lanzador {
    private final static String directorioGenerarClasses = "target";
    private final static String rutaSource = "src\\main\\java";

    public static void main(String[] args) {
        Lanzador p = new Lanzador();
        p.Compila();
        p.ejecutaProcesoCompila();
    } 
    
    public void ejecutaProcesoCompila() {
        // Ejecutar la clase compilada usando su nombre de clase, no la ruta al .java
    	//doy dos argumentos hola y adios
        String[] comando = { "java", "-cp", directorioGenerarClasses, "Tema1.Gestiona","feo","Adios" };
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true);
            pb.inheritIO(); 
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    public void Compila() {
        // Compilar el archivo fuente y generar el .class en el directorio target
        String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSource + "\\Tema1\\Gestiona.java" };
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

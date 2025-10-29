package EjercicioPalabras;

import java.io.IOException;

public class LanzadorContadoresPalabras {

    private final static String directorioGenerarClasses = "target";
    private final static String rutaSource = "src\\main\\java\\Tema1\\Gestiona.java";

    public static void main(String[] args) {
        LanzadorContadoresPalabras lanzador = new LanzadorContadoresPalabras();
        lanzador.compila();
        //lanzador.ejecutaCompilado();
    }
 
    public void compila() {
        String[] comando = {
            "javac",
            "-d", directorioGenerarClasses,
            rutaSource
        };

        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            Process proceso = pb.start();
            proceso.waitFor(); // Espera a que termine la compilación
            System.out.println("✅ Compilación completada.");
        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Error al compilar.");
            e.printStackTrace();
        }
    }

    public void ejecutaCompilado() {
        String[] comando = {
            "java",
            "-cp", directorioGenerarClasses,
            "EjercicioPalabras.LanzadorContadoresPalabras"
        };

        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.redirectErrorStream(true);
            pb.inheritIO();
            pb.start();
            System.out.println("🚀 Ejecutando clase compilada...");
        } catch (IOException e) {
            System.err.println("❌ Error al ejecutar.");
            e.printStackTrace();
        }
    }
}

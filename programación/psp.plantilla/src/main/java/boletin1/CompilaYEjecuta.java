package boletin1;

/*
 * EJERCICIO 4: Compilar y ejecutar en Java sin IDE
 * ------------------------------------------------------------
 * - Investiga qué hace 'javac' y cómo usar -d, -cp y -sourcepath
 * - Ejecuta los comandos desde Java con ProcessBuilder
 */

public class CompilaYEjecuta {
    public static void main(String[] args) throws Exception {
        // Compilamos una clase de ejemplo
        ProcessBuilder compila = new ProcessBuilder(
            "javac", "-d", "target/classes", "src/ejemplo/Hola.java"
        );
        compila.inheritIO();
        compila.start().waitFor();

        // Ejecutamos la clase compilada
        ProcessBuilder ejecuta = new ProcessBuilder(
            "java", "-cp", "target/classes", "ejemplo.Hola"
        );
        ejecuta.inheritIO();
        ejecuta.start().waitFor();
    }
}

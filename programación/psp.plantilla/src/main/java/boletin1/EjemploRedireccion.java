package boletin1;

/*
 * EJERCICIO 5: inheritIO() y redirectErrorStream(true)
 * ------------------------------------------------------------
 * - inheritIO(): redirige stdout y stderr del hijo al padre (mismo terminal)
 * - redirectErrorStream(true): combina los flujos de salida y error en uno solo
 */

public class EjemploRedireccion {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder("java", "MiClaseQueFalla");
            pb.redirectErrorStream(true);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


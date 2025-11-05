package pruebas;

import java.io.*;

public class GeneradorHaciendaDesdeDatos {

    // Rutas base del proyecto
    private static final String RUTA_SOURCE = "src/main/java/";
    private static final String RUTA_RESOURCES = "src/main/resources/";
    private static final String DIRECTORIO_GENERAR_CLASSES = "target/classes/";

    public static void main(String[] args) {

        // Nombre fijo del fichero de entrada
        String nombreFichero = "datos.txt";

        // Construimos las rutas completas de entrada y resumen
        String ficheroEntrada = RUTA_RESOURCES + nombreFichero;
        String ficheroResumen = RUTA_RESOURCES + "resumen.txt";

        GeneradorHaciendaDesdeDatos gh = new GeneradorHaciendaDesdeDatos();

        // Compilamos las clases hijas
        gh.compilaClase("pruebas/GeneraDNIFichero.java");
        gh.compilaClase("pruebas/GeneraNSSFichero.java");

        // Ejecutamos los procesos hijos y obtenemos los totales de contribuyentes
        int totalDNIs = gh.ejecutarYObtenerSalida("pruebas.GeneraDNIFichero", ficheroEntrada);
        int totalNSSs = gh.ejecutarYObtenerSalida("pruebas.GeneraNSSFichero", ficheroEntrada);

        
        int totalContribuyentes = totalDNIs + totalNSSs;
        // Imprimimos en consola el resumen final
        System.out.println("Número total de contribuyentes tratados:" + totalContribuyentes);
        System.out.println("DNIs tratados: " + totalDNIs);
        System.out.println("NSSs tratados: " + totalNSSs);

        // Escribimos el resumen final en el fichero
        gh.escribirResumen(ficheroResumen, totalDNIs, totalNSSs);
    }

    // Método que compila una clase Java usando javac
    public void compilaClase(String rutaRelativa) {
        String[] comando = {"javac", "--release", "17", "-d", DIRECTORIO_GENERAR_CLASSES, RUTA_SOURCE + rutaRelativa};

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO();
            int exit = pb.start().waitFor();

            if (exit == 0) {
                System.out.println("Compilación correcta de " + rutaRelativa);
            } else {
                System.err.println("Error compilando " + rutaRelativa);
                System.exit(exit);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error durante la compilación: " + e.getMessage());
            System.exit(1);
        }
    }

    // Método que ejecuta la clase hija y devuelve el número que imprime
    public int ejecutarYObtenerSalida(String clase, String ficheroEntrada) {
        int resultado = 0;

        try {
            String javaHome = System.getenv("JAVA_HOME"); 
            if (javaHome == null) {
                System.err.println("JAVA_HOME no definido. Asegúrate de tener JDK 17 configurado.");
                System.exit(1);
            }
            String javaCmd = javaHome + "/bin/java";

            String[] comando = {javaCmd, "-cp", DIRECTORIO_GENERAR_CLASSES, clase, ficheroEntrada};
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);

            Process proceso = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    try {
                        resultado = Integer.parseInt(linea); // la línea con el número
                    } catch (NumberFormatException e) {
                        System.out.println(linea); // imprime cualquier otro mensaje del hijo
                    }
                }
            }
            br.close();

            int exit = proceso.waitFor();
            if (exit != 0) {
                System.err.println("Padre: error en hijo (" + clase + ")");
                System.exit(exit);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error ejecutando " + clase + ": " + e.getMessage());
            System.exit(1);
        }

        return resultado;
    }

    // Método que escribe el resumen final de DNIs y NSSs
    public void escribirResumen(String ficheroResumen, int totalDNIs, int totalNSSs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroResumen))) {
            bw.write("Número total de contribuyentes tratados:\n");
            bw.write("DNIs tratados: " + totalDNIs + "\n");
            bw.write("NSSs tratados: " + totalNSSs + "\n");
            bw.write("TOTAL: " + (totalDNIs + totalNSSs) + "\n");
        } catch (IOException e) {
            System.err.println("Error escribiendo el resumen: " + e.getMessage());
        }
    }
}

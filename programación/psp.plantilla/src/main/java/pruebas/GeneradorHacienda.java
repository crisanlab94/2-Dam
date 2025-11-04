package pruebas;

import java.io.*;
import java.util.Scanner;

public class GeneradorHacienda {

    // Rutas base del proyecto
    private static final String RUTA_SOURCE = "src/main/java/";
    private static final String RUTA_RESOURCES = "src/main/resources/";
    private static final String DIRECTORIO_GENERAR_CLASSES = "target/classes/";

    public static void main(String[] args) {

        // Pedimos al usuario el nombre del fichero de entrada
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el nombre del fichero de entrada (por ejemplo datos.txt): ");
        String nombreFichero = sc.nextLine().trim();
        sc.close();

        // Construimos las rutas completas de entrada y resumen
        String ficheroEntrada = RUTA_RESOURCES + nombreFichero;
        String ficheroResumen = RUTA_RESOURCES + "resumen.txt";

        // Compilamos las clases hijas
        GeneradorHacienda gh = new GeneradorHacienda();
        gh.compilaClase("pruebas/GeneraDNIFichero.java");
        gh.compilaClase("pruebas/GeneraNSSFichero.java");

        // Ejecutamos los procesos hijos y obtenemos los totales de contribuyentes
        int totalDNIs = gh.ejecutarYObtenerSalida("pruebas.GeneraDNIFichero", ficheroEntrada);
        int totalNSSs = gh.ejecutarYObtenerSalida("pruebas.GeneraNSSFichero", ficheroEntrada);

        // Escribimos el resumen final con los totales
        gh.escribirResumen(ficheroResumen, totalDNIs, totalNSSs);
    }

    // Método que compila una clase Java usando javac
    public void compilaClase(String rutaRelativa) {
        // Construimos el comando de compilación con la versión Java 17
        String[] comando = {"javac", "--release", "17", "-d", DIRECTORIO_GENERAR_CLASSES, RUTA_SOURCE + rutaRelativa};

        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true); // redirige errores al mismo flujo que la salida
            pb.inheritIO(); // muestra salida y errores en la consola del padre
            int exit = pb.start().waitFor(); // ejecuta el comando y espera a que termine

            if (exit == 0) {
                System.out.println("Compilación correcta de " + rutaRelativa);
            } else {
                System.err.println("Error compilando " + rutaRelativa);
                System.exit(exit); // si hay error, se detiene el programa
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error durante la compilación: " + e.getMessage());
            System.exit(1);
        }
    }

    public int ejecutarYObtenerSalida(String clase, String ficheroEntrada) {
        int resultado = 0;

        try {
            // Comando para ejecutar la clase hija
           //En clase asi
        	//String[] comando = {"java", "-cp", "target/classes", clase, ficheroEntrada};
            
        	//Para a casa
        	// Obtiene la ruta del JDK 17 desde JAVA_HOME
        	String javaHome = System.getenv("JAVA_HOME"); 
        	String javaCmd = javaHome + "/bin/java"; // En Windows sería "\\bin\\java"

        	// Construye el comando usando la JVM correcta
        	String[] comando = {javaCmd, "-cp", "target/classes", clase, ficheroEntrada};

        	//A partir de aqui igual en clase
        	
        	ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true); // errores y salida estándar juntos

            Process proceso = pb.start(); // ejecuta el proceso hijo

            // Leemos la salida del hijo sin usar try-with-resources anidado
            BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea = br.readLine(); // asumimos que el hijo imprime solo un número
            if (linea != null) {
                // Convertimos la cadena en un número entero
                resultado = Integer.parseInt(linea.trim());
            }
            br.close(); // cerramos manualmente

            // Esperamos a que el proceso hijo termine
            int exit = proceso.waitFor();

            // Si el hijo terminó con error, propagamos el error al padre
            if (exit != 0) {
                System.err.println("Padre: Propaga error hijo (" + clase + ")");
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
            bw.write("TOTAL: " + (totalDNIs + totalNSSs)); // suma de los totales
        } catch (IOException e) {
            System.err.println("Error escribiendo el resumen: " + e.getMessage());
        }
    }
}

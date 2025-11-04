package dam.psp34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class GeneradorHacienda {
    private static final String rutaResources = "src/main/resources/";
    private static final String directorioGenerarClasses = "target/classes";
    private static final String paquete = "dam.psp34.";

    public static void main(String[] args) {
        GeneradorHacienda lanzador = new GeneradorHacienda();

        System.out.print("Pon la ruta del fichero (ej: datos.txt): ");
        Scanner scanner = new Scanner(System.in);
        String rutaDatos = scanner.nextLine();
        scanner.close();

        rutaDatos = rutaResources + rutaDatos; 
        System.out.println("Ruta completa: " + rutaDatos); 
        int totalNSS = 0;
        int totalDNI = 0;
        

        if (lanzador.compila("GeneraNSSFichero")) {
            totalNSS = lanzador.lanzarYLeerProceso("GeneraNSSFichero", rutaDatos);
        }

        if (lanzador.compila("GeneraDNIFichero")) {
             totalDNI = lanzador.lanzarYLeerProceso("GeneraDNIFichero", rutaDatos);
 
        }
        lanzador.escribeResumenDNI(totalDNI, totalNSS);

        
    }

    public int lanzarYLeerProceso(String clase, String rutaFichero) {
        int resultado = 0;
        String claseCompleta = paquete + clase;

        String[] comando = {
            "java", "-cp", directorioGenerarClasses,
            claseCompleta,
            rutaFichero
        }; 

        Process proceso = null;
        BufferedReader lector = null;
        BufferedReader errores = null;

        try {
            ProcessBuilder builder = new ProcessBuilder(comando);
            proceso = builder.start();

            lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            errores = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));

            String linea = lector.readLine();
            if (linea != null) {
                System.out.println("Clase hija: " + clase + " → " + linea);
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    resultado = Integer.parseInt(partes[1].trim());
                }
            }

            int codigo = proceso.waitFor();
            if (codigo != 0) {
                String error;
                while ((error = errores.readLine()) != null) {
                    System.err.println("Error en " + clase + ": " + error);
                }
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Fallo al lanzar " + clase + ": " + e.getMessage());
        } finally {
            try {
                if (lector != null) lector.close();
                if (errores != null) errores.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar flujos: " + e.getMessage());
            }
        }

        return resultado;
    }

    public boolean compila(String clase) {
        String rutaFuente = "src/main/java/dam/psp34/" + clase + ".java";
        String destino = directorioGenerarClasses;
        boolean compilacionExitosa = false;

        Process proceso = null;
        BufferedReader lector = null;

        try {
            String[] comando = { "javac", "-d", destino, rutaFuente };
            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);

            proceso = pb.start();
            lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println("linea " + linea);
            }

            int codigoSalida = proceso.waitFor();
            if (codigoSalida == 0) {
                System.out.println("Compilación correcta de " + clase);
                compilacionExitosa = true;
            } else {
                System.err.println("Error al compilar " + clase + ". Código: " + codigoSalida);
            }
 
        } catch (IOException | InterruptedException e) {
            System.err.println("Excepción durante la compilación de " + clase + ": " + e.getMessage());
        } finally {
            try { 
                if (lector != null) lector.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar lector: " + e.getMessage());
            }
        }

        return compilacionExitosa;
    }
    
    public void escribeResumenDNI(int dni, int nss) { 
    		PrintWriter pw = null;
    		try {  
    			pw = new PrintWriter("src/main/resources/resumen.txt");
    			pw.println("Número total de contribuyentes tratados:"); 
    			pw.println("DNIs Tratados:" + dni); 
    			pw.println("NSSs Tratados:" + nss);  


    		} catch (IOException e) { 
    			e.printStackTrace();
    		} finally {
    			if (pw != null)
    				pw.close();
    		}
    	
    }
 
	
}
    


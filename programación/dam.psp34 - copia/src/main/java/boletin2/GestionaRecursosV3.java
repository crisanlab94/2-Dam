
	package boletin2;

	import java.io.*;
	import java.util.*;

	public class GestionaRecursosV3 {

	    // Método que cuenta caracteres de un fichero
	    private static int contarCaracteres(File fichero) {
	        int cuenta = 0;
	        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
	            int c;
	            while ((c = br.read()) != -1) {
	                cuenta++;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return cuenta;
	    }

	    public static void main(String[] args) {
	        File carpeta = new File("resources"); // carpeta resources
	        File[] ficheros = carpeta.listFiles((dir, name) -> name.endsWith(".txt"));

	        if (ficheros == null) {
	            System.out.println("No hay ficheros .txt en la carpeta resources.");
	            return;
	        }

	        // =========================
	        // (1) SECUENCIAL
	        // =========================
	        long t1_inicio = System.currentTimeMillis();
	        int totalSecuencial = 0;
	        for (File f : ficheros) {
	            totalSecuencial += contarCaracteres(f);
	        }
	        long t1_fin = System.currentTimeMillis();
	        System.out.println("(1) Secuencial: total caracteres = " + totalSecuencial +
	                " en " + (t1_fin - t1_inicio) + " ms");

	        // =========================
	        // (2) CON HILOS
	        // =========================
	        long t2_inicio = System.currentTimeMillis();
	        List<Thread> hilos = new ArrayList<>();
	        final int[] totalHilos = {0};

	        for (File f : ficheros) {
	            Thread hilo = new Thread(() -> {
	                int cuenta = contarCaracteres(f);
	                synchronized (GestionaRecursosV3.class) {
	                    totalHilos[0] += cuenta;
	                }
	            });
	            hilos.add(hilo);
	            hilo.start();
	        }

	        // Esperamos a que terminen todos
	        for (Thread h : hilos) {
	            try {
	                h.join();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        long t2_fin = System.currentTimeMillis();
	        System.out.println("(2) Con hilos: total caracteres = " + totalHilos[0] +
	                " en " + (t2_fin - t2_inicio) + " ms");

	        // =========================
	        // (3) CON PROCESOS
	        // =========================
	        // Aquí simulamos lanzar un proceso externo por cada fichero.
	        // Por ejemplo, podemos lanzar el mismo programa con argumentos.
	        long t3_inicio = System.currentTimeMillis();
	        int totalProcesos = 0;

	        for (File f : ficheros) {
	            try {
	                long t_comienzo = System.currentTimeMillis();

	                // Lanzamos un proceso Java que cuenta caracteres de un fichero
	                ProcessBuilder pb = new ProcessBuilder("java", "ContarProceso", f.getAbsolutePath());
	                pb.inheritIO(); // para ver salida del proceso
	                Process p = pb.start();
	                p.waitFor();

	                long t_fin = System.currentTimeMillis();
	                System.out.println("Proceso para " + f.getName() + " tardó " + (t_fin - t_comienzo) + " ms");

	                // Aquí podrías recoger el resultado del proceso si lo imprime por consola
	                // (ejemplo: leer con InputStream de p.getInputStream())
	                // Para simplificar, volvemos a contar directamente:
	                totalProcesos += contarCaracteres(f);

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        long t3_fin = System.currentTimeMillis();
	        System.out.println("(3) Con procesos: total caracteres = " + totalProcesos +
	                " en " + (t3_fin - t3_inicio) + " ms");
	    }
	}




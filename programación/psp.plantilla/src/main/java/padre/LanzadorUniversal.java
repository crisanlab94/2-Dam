package padre;

import java.io.*;

public class LanzadorUniversal {

    // ============================
    // CONFIGURACIÓN DE RUTAS
    // ============================
    private static final String RUTA_SOURCE = "src/main/java/";
    private static final String RUTA_CLASSES = "target/classes";
    private static final String DIRECTORIO_GENERAR_CLASSES = "target";

    public static void main(String[] args) {
        LanzadorUniversal lanzador = new LanzadorUniversal();

        // Compila el hijo
        lanzador.compilaClase("dam/psp/HijoUniversal.java");

        // Ejemplo de argumentos
        String[] provincias = {"Sevilla", "Granada", "Cádiz"};
        int[] numeros = {5, 12, 25};

        // Lanza hijos con String
        lanzador.lanzarProcesosConStrings(provincias);

        // Lanza hijos con enteros
        lanzador.lanzarProcesosConEnteros(numeros);
   
        // Lanza un proceso hijo por cada provincia
        lanzador.lanzarProcesosYMostrarTotales(provincias);
    }

    
    
    // =====================================================
    // MÉTODO PARA COMPILAR UNA CLASE HIJO
    // =====================================================
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

    // =====================================================
    // LANZAR HIJOS CON ARGUMENTOS STRING
    // =====================================================
    public void lanzarProcesosConStrings(String[] argsString) {
        int total = 0;
        for (String arg : argsString) {
            String[] comando = {"java", "-cp", RUTA_CLASSES, "dam.psp.HijoUniversal", arg};

            try {
            	 ProcessBuilder pb = new ProcessBuilder(comando);
                 pb.redirectErrorStream(false); // salida y error separados
                 Process proceso = pb.start();

                 // Lectura de salida estándar
                 BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                 String linea, ultima = null;
                 while ((linea = salida.readLine()) != null) {
                     ultima = linea.trim();
                     System.out.println("Padre recibe → " + ultima);
                 }

                 // Espera a que termine
                 int exit = proceso.waitFor();

                 // 🔹 NUEVO: manejo de errores del hijo
                 if (exit != 0) {
                     BufferedReader errorReader = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                     StringBuilder mensajeError = new StringBuilder();
                     String errorLinea;
                     while ((errorLinea = errorReader.readLine()) != null) {
                         mensajeError.append(errorLinea).append("\n");
                     }
                     errorReader.close();

                     throw new RuntimeException("❌ Error en proceso hijo (" + arg + "):\n" + mensajeError);
                 }

                 // Procesar salida si fue correcta
                 if (ultima != null && ultima.matches(".*\\d+.*")) {
                     String numero = ultima.replaceAll("\\D+", "");
                     if (!numero.isEmpty()) total += Integer.parseInt(numero);
                 }

                 salida.close();

             } catch (IOException | InterruptedException e) {
                 System.err.println("Error ejecutando proceso para " + arg + ": " + e.getMessage());
             } catch (RuntimeException ex) {
                 // Capturamos y mostramos el error lanzado arriba
                 System.err.println(ex.getMessage());
             }
         }
         System.out.println("TOTAL acumulado String: " + total);
     }

    // =====================================================
    // LANZAR HIJOS CON ARGUMENTOS ENTEROS
    // =====================================================
    public void lanzarProcesosConEnteros(int[] argsInt) {
        int suma = 0;
        for (int n : argsInt) {
            String[] comando = {"java", "-cp", RUTA_CLASSES, "dam.psp.HijoUniversal", String.valueOf(n)};

            try {
                ProcessBuilder pb = new ProcessBuilder(comando);
                Process proceso = pb.start();

                BufferedReader salida = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
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

                if (ultima != null && ultima.matches(".*\\d+.*")) {
                    String numero = ultima.replaceAll("\\D+", "");
                    if (!numero.isEmpty()) suma += Integer.parseInt(numero);
                }

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" TOTAL acumulado int: " + suma);
    }
    
    /**
     * Método que lanza un proceso hijo por cada argumento recibido (por ejemplo, cada provincia),
     * recoge la salida de cada proceso y calcula un total global.
     */
    /**
     * Método que lanza un proceso hijo por cada argumento recibido (por ejemplo, cada provincia),
     * recoge la salida de cada proceso y calcula un total global.
     * Si el proceso hijo devuelve un error, el padre lanza una excepción con el mensaje de error.
     */
    public void lanzarProcesosYMostrarTotales(String[] argumentos) {
        int totalPedidos = 0;  // para sumar los pedidos de todos los hijos

        for (String argumento : argumentos) {
            String[] comando = {
                "java", "-cp", "target/classes", "dam.psp.HijoUniversal", argumento
            };

            ProcessBuilder pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(false); // mantenemos error y salida separados

            try {
                Process proceso = pb.start();

                // Lectura de la salida estándar del hijo
                BufferedReader salida = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
                );

                String linea;
                String ultimaLinea = null;
                while ((linea = salida.readLine()) != null) {
                    ultimaLinea = linea.trim();
                    System.out.println("Padre recibe → " + ultimaLinea);
                }

                // Espera a que el hijo termine
                int exit = proceso.waitFor();

                // 🔹 Nuevo bloque: manejo de errores del hijo
                if (exit != 0) {
                    BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(proceso.getErrorStream())
                    );
                    StringBuilder mensajeError = new StringBuilder();
                    String errorLinea;
                    while ((errorLinea = errorReader.readLine()) != null) {
                        mensajeError.append(errorLinea).append("\n");
                    }
                    errorReader.close();

                    // Lanza excepción con el detalle del error
                    throw new RuntimeException("Error en proceso hijo (" + argumento + "):\n" + mensajeError);
                }

                // Si no hubo error, procesamos el resultado numérico
                if (ultimaLinea != null && ultimaLinea.matches(".*\\d+.*")) {
                    String soloNumero = ultimaLinea.replaceAll("\\D+", "");
                    if (!soloNumero.isEmpty()) {
                        totalPedidos += Integer.parseInt(soloNumero);
                    }
                }

                salida.close();

            } catch (IOException | InterruptedException e) {
                System.err.println("Error ejecutando proceso para " + argumento + ": " + e.getMessage());
            } catch (RuntimeException ex) {
                // Captura la excepción lanzada manualmente
                System.err.println(ex.getMessage());
            }
        }

        System.out.println("Nº total de Pedidos : " + totalPedidos);
    }

}

package padre;

import java.io.*;
import java.nio.file.*;

public class LanzadorComprobarFichero {
    private static final String RUTA_RESOURCES = "src/main/resources/";

    // =============================
    // MÉTODO PARA COMPROBAR Y CREAR FICHERO BASE
    // =============================
    public void preparaFicheroPedidos() {
        String rutaFichero = RUTA_RESOURCES + "pedidos.txt";
        File fichero = new File(rutaFichero);

        try {
            // Si no existe, lo creamos con contenido de ejemplo
            if (!fichero.exists()) {
                System.out.println("📄 No existe pedidos.txt — creando fichero de ejemplo...");

                // Nos aseguramos de que la carpeta existe
                Files.createDirectories(Paths.get(RUTA_RESOURCES));

                // Creamos el fichero y escribimos contenido de ejemplo
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichero))) {
                    bw.write("Sevilla;pedido1\n");
                    bw.write("Cádiz;pedido2\n");
                    bw.write("Granada;pedido3\n");
                    bw.write("Sevilla;pedido4\n");
                    bw.write("Huelva;pedido5\n");
                    bw.write("Málaga;pedido6\n");
                    bw.write("Córdoba;pedido7\n");
                }

                System.out.println("✅ Fichero 'pedidos.txt' creado con contenido de prueba.");
            } else {
                System.out.println("✅ Fichero 'pedidos.txt' ya existe. Continuando...");
            }
        } catch (IOException e) {
            System.err.println("❌ Error preparando fichero de pedidos: " + e.getMessage());
        }
    }

    // ... aquí irían los demás métodos (compilaClase, lanzarProcesosYMostrarTotales, etc.)
}

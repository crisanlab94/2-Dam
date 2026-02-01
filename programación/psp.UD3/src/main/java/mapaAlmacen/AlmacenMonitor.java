package mapaAlmacen;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class AlmacenMonitor {
	private Map<Integer, Estado> estados;
    private Map<Integer, Boolean> urgencias;

   
    public AlmacenMonitor() {
        this.estados = new HashMap<>();
        this.urgencias = new HashMap<>();
    }
	    // Método sincronizado para actualizar
	    public synchronized void actualizarPedido(int id, Estado nuevoEstado, boolean esUrgente) {
	        estados.put(id, nuevoEstado);
	        urgencias.put(id, esUrgente);
	        generarInforme();
	        System.out.println("Pedido " + id + " actualizado a " + nuevoEstado + " (Urgente: " + esUrgente + ")");
	    }

	    public synchronized void generarInforme() {
	        // 1. Definimos la ruta relativa
	        String ruta = "src/main/resources/informe_diario.txt";
	        File archivo = new File(ruta);
	        
	        // 2. Obtenemos la carpeta (src/main/resources)
	        File carpeta = archivo.getParentFile();

	        try {
	            // 3. ¡ESTA ES LA CLAVE! Si la carpeta no existe, la creamos
	            if (carpeta != null && !carpeta.exists()) {
	                carpeta.mkdirs(); // mkdirs() crea toda la estructura de carpetas si no existe
	                System.out.println("✅ Carpetas creadas: " + carpeta.getPath());
	            }

	            // 4. Ahora ya podemos crear el PrintWriter sin miedo
	            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
	                writer.println("--- INFORME DE ALMACÉN ---");
	                for (Integer id : estados.keySet()) {
	                    Estado e = estados.get(id);
	                    Boolean u = urgencias.getOrDefault(id, false);
	                    writer.println("Pedido ID: " + id + " | Estado: " + e + " | Urgente: " + (u ? "SÍ" : "NO"));
	                }
	                // Imprimimos la ruta completa para que la busques en tu PC
	                System.out.println("📄 ARCHIVO CREADO EN: " + archivo.getAbsolutePath());
	            }
	        } catch (Exception e) {
	            System.err.println("❌ ERROR CRÍTICO: " + e.getMessage());
	        }
	    }
	}



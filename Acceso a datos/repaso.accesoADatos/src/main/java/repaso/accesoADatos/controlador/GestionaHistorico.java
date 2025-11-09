package repaso.accesoADatos.controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import repaso.accesoADatos.modelo.Combinaciones;
import repaso.accesoADatos.repositorio.HistoricoCombinaciones;
import repaso.accesoADatos.servicio.ServicioEstadisticasCombinaciones;
import repaso.accesoADatos.utiles.ManejaCombinaciones;

public class GestionaHistorico {

    private static final Logger logger = Logger.getLogger(GestionaHistorico.class.getName());

    public static void main(String[] args) {
        // Repositorio histórico donde se almacenarán todas las combinaciones
        HistoricoCombinaciones repoHistorico = new HistoricoCombinaciones();

        // Util para leer el fichero CSV
        ManejaCombinaciones util = new ManejaCombinaciones();

        try {
            // Ruta del CSV en src/main/resources
            String rutaCSV = "src/main/resources/combinaciones.csv";

            // Llamamos al método de la clase util para cargar combinaciones
            util.cargarCombinaciones(rutaCSV, repoHistorico);
            logger.info("Combinaciones cargadas correctamente desde el CSV.");

            // Creamos el servicio de estadísticas pasándole el repositorio
            ServicioEstadisticasCombinaciones servicio = new ServicioEstadisticasCombinaciones(repoHistorico);

            // Calculamos la combinación más frecuente
            Combinaciones combinacionMas = servicio.combinacionMasFrecuente();
            logger.info("Combinación más frecuente calculada: " + combinacionMas);

            // Calculamos la estrella más repetida
            int estrellaMas = servicio.estrellaMasRepetida();
            logger.info("Estrella más frecuente calculada: " + estrellaMas);

            // Guardamos un fichero resumen en texto
            String rutaResumenTxt = "src/main/resources/resumen.txt";
            escribirResumen(rutaResumenTxt, combinacionMas, estrellaMas);
            logger.info("Resumen TXT generado en: " + rutaResumenTxt);
            
            // ---- Aquí convertimos el resumen.txt a JSON ----
            String rutaResumenJson1 = "src/main/resources/resumen.json";
            pasarResumenTxtAJson(rutaResumenTxt, rutaResumenJson1);
            logger.info("Resumen convertido a JSON en: " + rutaResumenJson1);

            // Guardamos el resumen también en JSON usando Gson
            String rutaResumenJson = "src/main/resources/resumen.json";
            escribirResumenJSONGson(servicio, rutaResumenJson);
            logger.info("Resumen JSON generado en: " + rutaResumenJson);

        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "No se encontró el fichero CSV: " + e.getMessage(), e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al escribir el fichero resumen: " + e.getMessage(), e);
        }
    }

    // Método que escribe un fichero resumen en texto
    public static void escribirResumen(String ruta, Combinaciones combinacionMas, int estrellaMas) 
            throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            pw.println("Combinación más frecuente: " + combinacionMas);
            pw.println("Estrella más frecuente: " + estrellaMas);
        }
    }

    // Método que escribe un fichero resumen en JSON usando Gson
    public static void escribirResumenJSONGson(ServicioEstadisticasCombinaciones servicio, String ruta) {
        Map<String, Object> resumen = new HashMap<>();
        resumen.put("combinacionMasFrecuente", servicio.combinacionMasFrecuente().getNumeros());
        resumen.put("estrellaMasFrecuente", servicio.estrellaMasRepetida());
        resumen.put("vecesCadaCombinacion", servicio.vecesCadaCombinacion());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(ruta)) {
            gson.toJson(resumen, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
 // Método que lee resumen.txt y lo pasa a resumen.json
    public static void pasarResumenTxtAJson(String rutaTxt, String rutaJson) {
        Map<String, Object> resumen = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaTxt))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Combinación más frecuente:")) {
                    // Extraemos los números de la combinación
                    String combinacion = linea.substring("Combinación más frecuente:".length()).trim();
                    resumen.put("combinacionMasFrecuente", combinacion);
                } else if (linea.startsWith("Estrella más frecuente:")) {
                    // Extraemos la estrella más frecuente
                    String estrella = linea.substring("Estrella más frecuente:".length()).trim();
                    resumen.put("estrellaMasFrecuente", estrella);
                }
            }

            // Escribimos el JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(rutaJson)) {
                gson.toJson(resumen, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}

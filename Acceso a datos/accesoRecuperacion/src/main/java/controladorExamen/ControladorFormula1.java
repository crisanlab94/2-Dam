package controladorExamen;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Util.DOMEquipo;
import modeloExamen.Equipo;
import modeloExamen.Piloto;
import repositorioExamen.RepositorioEquipos;
import servicioExamen.ServicioEquipos;



public class ControladorFormula1 {
    
    private static final Logger logger = LogManager.getLogger(ControladorFormula1.class);

    public static void main(String[] args) {
        // Inicialización de la arquitectura
        RepositorioEquipos repoEq = new RepositorioEquipos();
        ServicioEquipos servicio = new ServicioEquipos(repoEq);
        DOMEquipo dom = new DOMEquipo();

        try {
            // --- PASO 1: CARGA DE EQUIPOS ---
            // Los equipos entran con la lista de pilotos vacía
            List<Equipo> listaEquiposXML = dom.leerEquiposDesdeXML("formula1.xml");
            servicio.cargarEquipos(listaEquiposXML);

            // --- PASO 2: CARGA DE LISTA DE PILOTOS ---
            // Lista independiente de pilotos extraída del mismo XML
            List<Piloto> listaPilotosAux = dom.leerPilotosDesdeXML("formula1.xml");

            // --- PASO 3: RELLENAR COLECCIÓN DE PILOTOS EN CADA EQUIPO ---
            // El cruce de datos: asignamos cada piloto a su equipo en el repositorio
            servicio.vincularPilotosAEquipos(listaPilotosAux);

            // --- PASO 4: FILTRADO Y GENERACIÓN DE JSON ---
            // Buscamos pilotos con más de 4 puntos y exportamos a JSON
            List<Piloto> pilotosTop = servicio.filtrarPilotosPorPuntos(4);
            try (java.io.FileWriter writer = new java.io.FileWriter(DOMEquipo.rutaResources + "pilotos_top.json")) {
                com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
                gson.toJson(pilotosTop, writer);
                logger.error("Paso 4: JSON 'pilotos_top.json' generado con {} pilotos.", pilotosTop.size());
            }

            // --- PASO 5: GENERACIÓN DE LOS DOS FICHEROS XML ---
            
            // 1. XML con Estructura Básica (el que tenías al principio)
            dom.escribirFormula1XML("resultado_basico.xml", 
                                  new ArrayList<>(repoEq.getMapaEquipos().values()));
            
            // 2. XML con Estructura Completa y Anidada (el que pide el Paso 5)
            dom.escribirFormula1XMLpaso5("clasificacion_final_paso5.xml", 
                                       new ArrayList<>(repoEq.getMapaEquipos().values()));

            logger.error("Paso 5: Ambos ficheros XML generados con éxito.");

            // --- MOSTRAR RESULTADOS POR CONSOLA ---
            logger.error("--- RESUMEN DE CLASIFICACIÓN ---");
            for (Equipo equipo : repoEq.getMapaEquipos().values()) {
                logger.error("Equipo: {} | Nº Pilotos: {}", 
                            equipo.getNombreEquipo(), equipo.getListaPilotos().size());
            }

            logger.error("Proceso finalizado correctamente.");

        } catch (Exception e) {
            logger.error("ERROR CRÍTICO: {}", e.getMessage());
            e.printStackTrace();
        }
    }
}
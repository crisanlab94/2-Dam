package controladorExamen;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Util.DOM;
import modeloExamen.Enfrentamiento;
import modeloExamen.EquipoExamen;
import repositorioExamen.EnfrentamientoRepositorio;
import repositorioExamen.EquipoRepositorio;
import servicioExamen.ServicioExamen;

public class ControladorTorneo {
    // Logger para registrar info, advertencias y errores
    private static final Logger logger = LogManager.getLogger(ControladorTorneo.class);

    public static void main(String[] args) {
        // 1. Inicializar Repositorios y Servicios
        EquipoRepositorio repoEq = new EquipoRepositorio();
        EnfrentamientoRepositorio repoEnf = new EnfrentamientoRepositorio();
        ServicioExamen servicio = new ServicioExamen(repoEq, repoEnf);
        DOM dom = new DOM();

        try {
            // 2. LEER EQUIPOS (Solo en el Main) (Nombre real del archivo)
            List<EquipoExamen> listaEq = dom.leerEquiposDesdeXML("torneoGamer.xml");
            
            // 3. CARGAR EN REPOSITORIO (Aquí saltará el LOG del JDG repetido)
            servicio.cargarEquipos(listaEq);

            // 4. LEER ENFRENTAMIENTOS (Pasamos el repo para que los objetos se unan) (Nombre real del archivo)
            List<Enfrentamiento> listaEnf = dom.leerEnfrentamientosDesdeXML("torneoGamer.xml", repoEq);
            servicio.cargarEnfrentamientos(listaEnf);

            // 5. PRUEBA DE LÓGICA: Obtener ganados de un equipo y generar JSON
            List<Enfrentamiento> ganadosG2 = servicio.getEnfrentamientosGanados("G2");
            
            dom.generarJson(ganadosG2, "ganadoresG2.json");
            
            //Generar CSV
            dom.generarCSV(repoEnf.getListaEnfrentamientos(), "resumen_enfrentamientos.csv");
            
            // 6. ESCRIBIR XML DE RESULTADOS (El nombre que le quieras dar al archivo)
            dom.escribirTorneoXML("resultado_final.xml", 
                                  new ArrayList<>(repoEq.getMapaEquipos().values()), 
                                  repoEnf.getListaEnfrentamientos());

            // --- cargar todo --- 
            // CAMBIO: Usamos .error para que se vea en consola si el nivel INFO está bloqueado
            logger.error(" Proceso finalizado con éxito.");

            // --- MOSTRAR RESULTADOS POR CONSOLA ---
            logger.error("--- LISTA DE EQUIPOS EN EL REPOSITORIO ---");
            for (EquipoExamen equipo : repoEq.getMapaEquipos().values()) {
                logger.error("Código: {} | Nombre: {} | Email: {}", 
                            equipo.getCodigo(), equipo.getNombre(), equipo.getEmail());
            }

            logger.error("--- LISTA DE ENFRENTAMIENTOS CARGADOS ---");
            for (Enfrentamiento enf : repoEnf.getListaEnfrentamientos()) {
                // Relación de objetos
                logger.error("ID: {} | Juego: {} | Ganador: {} | Contacto: {}", 
                            enf.getId(), 
                            enf.getVideojuego(), 
                            enf.getEquipoGanador().getNombre(), 
                            enf.getEquipoGanador().getEmail());
            }

        } catch (Exception e) {
            logger.error("Error crítico: {}", e.getMessage());
        }
    }
}
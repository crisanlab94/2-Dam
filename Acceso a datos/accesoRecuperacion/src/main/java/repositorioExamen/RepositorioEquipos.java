package repositorioExamen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modeloExamen.Equipo;
import modeloExamen.Piloto;



public class RepositorioEquipos {
    private static final Logger logger = LogManager.getLogger(RepositorioEquipos.class);
    private Map<String, Equipo> mapaEquipos;

    public RepositorioEquipos() {
        this.mapaEquipos = new HashMap<>();
    }

    // --- CRUD DE EQUIPOS ---
    
    public void agregarEquipo(Equipo e) throws Exception {
        if (mapaEquipos.containsKey(e.getIdentificadorEquipo())) {
            throw new Exception("ERROR: El equipo " + e.getIdentificadorEquipo() + " ya existe.");
        }
        mapaEquipos.put(e.getIdentificadorEquipo(), e);
    }

    public Equipo recuperarEquipo(String idEquipo) {
        return mapaEquipos.get(idEquipo);
    }

    public void agregarListaEquipos(List<Equipo> listaXML) {
        for (Equipo e : listaXML) {
            try {
                this.agregarEquipo(e);
            } catch (Exception ex) {
                logger.error("Error al cargar equipo: {} -> {}", e.getIdentificadorEquipo(), ex.getMessage());
            }
        }
    }

    // --- CRUD DE PILOTOS (Relacionados con el equipo) ---

    public void añadirPilotoAEquipo(String idEquipo, Piloto p) {
        Equipo eq = recuperarEquipo(idEquipo);
        if (eq != null) {
            // Se añade a la lista interna del modelo Equipo
            eq.getListaPilotos().add(p);
        } else {
            logger.warn("No se pudo vincular el piloto {}: El equipo {} no existe", p.getNombrePiloto(), idEquipo);
        }
    }

    public List<Piloto> recuperarPilotosDeEquipo(String idEquipo) {
        Equipo eq = recuperarEquipo(idEquipo);
        if (eq != null) {
            return eq.getListaPilotos();
        }
        return new ArrayList<>();
    }
    
    public List<Piloto> obtenerPilotosConPuntosMayores(int puntosCorte) {
        List<Piloto> filtrados = new ArrayList<>();
        // Recorremos todos los equipos del mapa
        for (Equipo eq : mapaEquipos.values()) {
            // Recorremos los pilotos de cada equipo
            for (Piloto p : eq.getListaPilotos()) {
                if (p.getPuntos() > puntosCorte) {
                    filtrados.add(p);
                }
            }
        }
        return filtrados;
    }

    // Getter para el mapa (usado en el Main para escribir el XML de salida)
    public Map<String, Equipo> getMapaEquipos() {
        return mapaEquipos;
    }
}
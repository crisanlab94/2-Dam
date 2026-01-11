package repositorioExamen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modeloExamen.BancoException;
import modeloExamen.CentroLogistico;
import modeloExamen.Tipo;
import modeloExamen.Trabajador;



public class BancoAlimentos {
    // Corregido el nombre de la clase en el Logger
    private static final Logger logger = LogManager.getLogger(BancoAlimentos.class);
    private Map<String, CentroLogistico> mapaCentros;

    public BancoAlimentos() {
        this.mapaCentros = new HashMap<>();
    }

    // --- MÉTODOS APARTADO 1 ---

    // 1. Agregar un centro logístico (Lanza BancoException si ya existe)
    public void agregarCentro(CentroLogistico centro) throws BancoException {
        if (mapaCentros.containsKey(centro.getIdCentro())) {
            throw new BancoException("ERROR: El centro logístico " + centro.getIdCentro() + " ya existe.");
        }
        mapaCentros.put(centro.getIdCentro(), centro);
    }

    // 2. Agregar un trabajador a un centro (Lanza BancoException si ya está asociado)
    public void agregarTrabajadorACentro(String idCentro, Trabajador t) throws BancoException {
        CentroLogistico c = recuperarCentro(idCentro);
        
        if (c != null) {
            // Comprobamos si el trabajador ya existe en este centro por su DNI
            for (Trabajador trab : c.getListaTrabajadores()) {
                if (trab.getDni().equals(t.getDni())) {
                    throw new BancoException("ERROR: El trabajador con DNI " + t.getDni() + " ya está asociado al centro " + idCentro);
                }
            }
            c.getListaTrabajadores().add(t);
        } else {
            throw new BancoException("ERROR: El centro " + idCentro + " no existe.");
        }
    }

    // 3. Obtener datos de un centro logístico a partir de su identificador
    public CentroLogistico recuperarCentro(String idCentro) {
        return mapaCentros.get(idCentro);
    }

    // 4. Obtener datos de un colaborador (Trabajador) a partir de su DNI
    public Trabajador obtenerColaboradorPorDni(String dni) {
        for (CentroLogistico centro : mapaCentros.values()) {
            for (Trabajador t : centro.getListaTrabajadores()) {
                if (t.getDni().equals(dni)) {
                    return t;
                }
            }
        }
        return null;
    }

    // --- MÉTODOS AUXILIARES ---

    public List<Trabajador> recuperarTrabajadorDeCentro(String idCentro) {
        CentroLogistico c = recuperarCentro(idCentro);
        return (c != null) ? c.getListaTrabajadores() : new ArrayList<>();
    }

    public void agregarListaCentros(List<CentroLogistico> listaXML) {
        for (CentroLogistico c : listaXML) {
            try {
                this.agregarCentro(c);
            } catch (BancoException ex) {
                logger.error("Error al cargar centro: {} -> {}", c.getIdCentro(), ex.getMessage());
            }
        }
    }

    public Map<String, CentroLogistico> getMapaCentros() {
        return mapaCentros;
    }
    
    

    public List<Trabajador> getColaboradoresPorTipo(Tipo tipo) {
        List<Trabajador> filtrados = new ArrayList<>();
        // Recorremos todos los centros
        for (CentroLogistico centro : mapaCentros.values()) {
            // Recorremos todos los trabajadores de cada centro
            for (Trabajador t : centro.getListaTrabajadores()) {
                if (t.getTipo().equals(tipo)) {
                    filtrados.add(t);
                }
            }
        }
        return filtrados;
    }
}
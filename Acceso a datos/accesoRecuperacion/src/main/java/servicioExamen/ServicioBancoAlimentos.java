package servicioExamen;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modeloExamen.BancoException;
import modeloExamen.CentroLogistico;
import modeloExamen.Tipo;
import modeloExamen.Trabajador;
import repositorioExamen.BancoAlimentos;



public class ServicioBancoAlimentos {
    private static final Logger logger = LogManager.getLogger(ServicioBancoAlimentos.class);
    private BancoAlimentos repoBancoAlimentos;

    public ServicioBancoAlimentos(BancoAlimentos repoBancoAlimentos) {
        this.repoBancoAlimentos = repoBancoAlimentos;
    }

    /**
     * PASO 1: Carga los centros en el repositorio.
     * @param listaCentrosXML Lista de objetos CentroLogistico leídos del XML.
     */
    public void cargarCentros(List<CentroLogistico> listaCentrosXML) {
        logger.info("Iniciando carga de centros en el sistema...");
        repoBancoAlimentos.agregarListaCentros(listaCentrosXML);
    }

    /**
     * PASO 3: Vincula una lista de trabajadores a sus centros correspondientes.
     * @param listaTrabajadoresXML Lista de objetos Trabajador leídos del XML.
     * @throws BancoException Si el trabajador ya existe en el centro o el centro no existe.
     */
    public void vincularTrabajadorACentro(List<Trabajador> listaTrabajadoresXML) throws BancoException {
        logger.info("Vinculando lista de trabajadores a sus centros correspondientes...");
        for (Trabajador t : listaTrabajadoresXML) {
            // Sacamos el identificador del centro donde trabaja este trabajador
            String idCentroDestino = t.getIdCentro(); 
            
            // Llamamos al repositorio para que lo guarde en el centro correcto
            repoBancoAlimentos.agregarTrabajadorACentro(idCentroDestino, t);
        }
    }
    
    /**
     * Obtiene la lista de trabajadores de un centro específico.
     */
    public List<Trabajador> obtenerTrabajadorDeUnCentro(String idCentro) {
        return repoBancoAlimentos.recuperarTrabajadorDeCentro(idCentro);
    }
    
    /**
     * Busca un trabajador por su DNI en todo el banco de alimentos.
     */
    public Trabajador buscarTrabajadorPorDni(String dni) {
        return repoBancoAlimentos.obtenerColaboradorPorDni(dni);
    }
    

    public List<Trabajador> obtenerColaboradoresPorTipo(Tipo tipo) {
        return repoBancoAlimentos.getColaboradoresPorTipo(tipo);
    }
}
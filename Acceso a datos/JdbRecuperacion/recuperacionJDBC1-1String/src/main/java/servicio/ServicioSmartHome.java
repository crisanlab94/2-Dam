package servicio;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Dispositivo;
import modelo.Tipo;
import repositorio.RepositorioSmartHome;
import util.MySqlConector;


public class ServicioSmartHome {

    private static final Logger logger = LogManager.getLogger(ServicioSmartHome.class);
    private MySqlConector conector;
    private RepositorioSmartHome repo;

    public ServicioSmartHome() throws AppException {
    	try {
    		 this.conector = new MySqlConector();
    		 this.repo = new RepositorioSmartHome(conector);
    	} catch (AppException e) {
	    	logger.error("Error al inicializar el servicio y la conexión", e);
	    	throw new AppException("Error al inicializar el servicio y la conexión: " + e.getMessage());
	 	}
       
    }
    
 
    // --- MÉTODOS DE GESTIÓN (CRUD) ---

    /**
     * Alta de dispositivo comprobando si el ID ya existe en la base de datos.
     */
    public void crearDispositivo(Dispositivo d) throws AppException {
        if (repo.buscarPorId(d.getId()) != null) {
            throw new AppException("ERROR: El dispositivo con ID " + d.getId() + " ya existe.");
        }
        repo.guardar(d);
        logger.info("Servicio: Dispositivo {} registrado con éxito.", d.getId());
    }

    /* Añadir solo detalle al dispositivo 
     * 
     * public void añadirDetalleADispositivo(String idPadre, Detalle nuevoDetalle) throws AppException {
    // 1. Buscamos si el padre existe de verdad en la BD
    Dispositivo padre = repo.buscarPorId(idPadre);
    
    if (padre == null) {
        throw new AppException("ERROR: No puedes añadir detalles a un dispositivo que no existe.");
    }
    
    // 2. En un 1:1, comprobamos si ya tiene detalle (opcional, según lógica)
    if (padre.getDetalle() != null && padre.getDetalle().getConsumo() > 0) {
        throw new AppException("ERROR: Este dispositivo ya tiene detalles registrados.");
    }

    // 3. Mandamos a guardar
    repo.guardarDetalleSolo(idPadre, nuevoDetalle);
}
     */
    
    
    public List<Dispositivo> listarTodo() throws AppException {
        return repo.read();
    }

    public void actualizarFirmware(String id, String version) throws AppException {
        repo.actualizarVersion(id, version);
        logger.info("Servicio: Firmware de {} actualizado.", id);
    }

    public void eliminarDispositivo(String id) throws AppException {
        repo.borrar(id);
        logger.warn("Servicio: Dispositivo {} eliminado.", id);
    }

    public void borrarTodo() throws AppException {
        // Suponiendo que el repo tiene un método para limpiar ambas tablas
        List<Dispositivo> todos = repo.read();
        for (Dispositivo d : todos) {
            repo.borrar(d.getId());
        }
        logger.info("Servicio: Limpieza total realizada.");
    }

    // --- MÉTODOS DE BÚSQUEDA Y FILTRADO ---

    public Dispositivo buscarPorId(String id) throws AppException {
        return repo.buscarPorId(id);
    }

    public List<Dispositivo> buscarAvanzado(String nombre, Tipo tipo) throws AppException {
        // Filtro que delega en el SQL del repositorio
        return repo.buscarNombreYTipo(nombre, tipo);
    }

    /**
     * Obtiene los 3 dispositivos con mayor consumo sin usar Lambdas.
     * Utiliza una clase anónima para el Comparator.
     */
    public List<Dispositivo> obtenerTop3Consumo() throws AppException {
        // 1. Obtenemos la lista actualizada de la base de datos
        List<Dispositivo> lista = repo.read();

        // 2. Ordenamos usando una clase anónima (Formato clásico)
        Collections.sort(lista, new Comparator<Dispositivo>() {
            @Override
            public int compare(Dispositivo d1, Dispositivo d2) {
                double consumo1 = d1.getDetalle().getConsumo();
                double consumo2 = d2.getDetalle().getConsumo();

                // Orden descendente (de mayor a menor)
                if (consumo1 < consumo2) return 1;
                if (consumo1 > consumo2) return -1;
                return 0;
            }
        });

        // 3. Extraemos el Top 3 de forma segura
        int limite = 3;
        if (lista.size() < 3) {
            limite = lista.size();
        }

        return lista.subList(0, limite);
    }
}

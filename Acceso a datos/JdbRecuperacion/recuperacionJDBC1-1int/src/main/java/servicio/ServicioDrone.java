package servicio;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import repositorio.RepositorioDrone;
import util.MySqlConector;

public class ServicioDrone {
    private static final Logger logger = LogManager.getLogger(ServicioDrone.class);
    private RepositorioDrone repo;

    public ServicioDrone() throws AppException {
        this.repo = new RepositorioDrone(new MySqlConector());
    }

    public void registrarDrone(Drone d) throws AppException {
        // VALIDACIÓN: Al ser ID manual, DEBEMOS comprobar si ya existe
        if (repo.buscarPorId(d.getId()) != null) {
            throw new AppException("ERROR: El Drone con ID " + d.getId() + " ya existe en el hangar.");
        }
        repo.guardar(d);
        logger.info("Servicio: Drone {} registrado correctamente.", d.getModelo());
    }

    public List<Drone> listarTodo() throws AppException {
        return repo.getLista();
    }

    public List<Drone> obtenerTop3Bateria() throws AppException {
        List<Drone> lista = new ArrayList<>(repo.getLista());
        // COMPARATOR CLÁSICO (Sin Lambdas)
        Collections.sort(lista, new Comparator<Drone>() {
            @Override
            public int compare(Drone d1, Drone d2) {
                // Orden descendente (de mayor batería a menor)
                if (d1.getNivelBateria() < d2.getNivelBateria()) return 1;
                if (d1.getNivelBateria() > d2.getNivelBateria()) return -1;
                return 0;
            }
        });
        return lista.subList(0, Math.min(lista.size(), 3));
    }

    public void mantenimiento(int id, int bateria) throws AppException { repo.actualizarBateria(id, bateria); }
    public void eliminarDrone(int id) throws AppException { repo.borrar(id); }
}

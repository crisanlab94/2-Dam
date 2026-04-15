package servicio;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import repositorio.RepositorioAstronauta;

public class ServicioSeguridad {
    private static final Logger logger = LogManager.getLogger(ServicioSeguridad.class);
    private final RepositorioAstronauta repo;

    public ServicioSeguridad(RepositorioAstronauta repo) {
        this.repo = repo;
    }

    /**
     * ALTA INTEGRAL: Valida que el astronauta traiga su seguridad
     * antes de mandarlo al repositorio.
     */
    public void registrarAstronautaCompleto(Astronauta a) throws AppException {
        // 1. Validaciones de integridad (Regla: Siempre con datos y seguridad)
        if (a.getNombre() == null || a.getNombre().trim().isEmpty()) {
            throw new AppException("Servicio: Nombre de astronauta obligatorio.");
        }
        
        if (a.getSeguridad() == null) {
            throw new AppException("Servicio: Error. No se puede registrar un astronauta sin su objeto de seguridad.");
        }

        // 2. Delegamos al repositorio. 
        // Él se encargará de insertar en las dos tablas bajo una transacción.
        repo.guardar(a);
        
        logger.info("Servicio: {} registrado correctamente con sus credenciales.", a.getNombre());
    }

    /**
     * LISTADO COMPLETO: Devuelve los astronautas con la seguridad ya inyectada.
     */
    public List<Astronauta> obtenerPersonalAutorizado() {
        // El repo ya hizo el JOIN en el método read(), así que los objetos están "llenos"
        return repo.getAstronautas();
    }
}
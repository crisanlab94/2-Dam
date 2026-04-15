package servicio;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import repositorio.RepositorioMedico;
import util.MySqlConector;

public class ServicioMedico {
    private static final Logger logger = LogManager.getLogger(ServicioMedico.class);
    private MySqlConector conector;
    private RepositorioMedico repo;

    public ServicioMedico() throws AppException {
        try {
            this.conector = new MySqlConector();
            this.repo = new RepositorioMedico(conector);
        } catch (AppException e) {
            logger.error("Error inicializando el servicio médico", e);
            throw new AppException("Error de conexión: " + e.getMessage());
        }
    }

    public void darAlta(Medico m) throws AppException {
        // En AI no buscamos ID, pero podemos validar que el email no exista en la caché
        for (Medico aux : repo.getLista()) {
            if (aux.getEmail().equals(m.getEmail())) {
                throw new AppException("ERROR: El email " + m.getEmail() + " ya está registrado.");
            }
        }
        repo.guardar(m);
        logger.info("Médico {} registrado correctamente.", m.getNombre());
    }

    public List<Medico> listarTodo() throws AppException {
        return repo.getLista();
    }

    public void modificarCoste(int id, double coste) throws AppException {
        repo.actualizarCoste(id, coste);
    }

    public void eliminar(int id) throws AppException {
        repo.borrar(id);
    }

    public List<Medico> obtenerTop3Costosos() throws AppException {
        List<Medico> lista = new ArrayList<>(repo.getLista());
        
        // ORDENACIÓN SIN LAMBDAS
        Collections.sort(lista, new Comparator<Medico>() {
            @Override
            public int compare(Medico m1, Medico m2) {
                double c1 = m1.getConsultorio().getCosteMantenimiento();
                double c2 = m2.getConsultorio().getCosteMantenimiento();
                if (c1 < c2) return 1;
                if (c1 > c2) return -1;
                return 0;
            }
        });

        int limite = Math.min(lista.size(), 3);
        return lista.subList(0, limite);
    }
}

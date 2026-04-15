package servicio;
import java.util.*;
import org.apache.logging.log4j.*;
import modelo.*;
import repositorio.*;
import util.MySqlConector;

public class ServicioUniversidad {
    private static final Logger logger = LogManager.getLogger(ServicioUniversidad.class);
    private RepositorioDepartamento repoDep;
    private RepositorioProfesor repoProf;

    public ServicioUniversidad() throws AppException {
        MySqlConector con = new MySqlConector();
        this.repoDep = new RepositorioDepartamento(con);
        this.repoProf = new RepositorioProfesor(con);
    }

    // --- ALTAS ---
    public void altaDepartamento(Departamento d) throws AppException {
        if (repoDep.buscarPorId(d.getId()) != null) throw new AppException("ID Departamento ya existe.");
        repoDep.guardar(d);
        logger.info("Servicio: Departamento {} creado.", d.getId());
    }

    public void altaProfesor(Profesor p, String idDep) throws AppException {
        if (repoDep.buscarPorId(idDep) == null) throw new AppException("Departamento no existe.");
        if (repoProf.buscarPorDni(p.getDni()) != null) throw new AppException("DNI ya registrado.");
        repoProf.guardar(p, idDep);
        logger.info("Servicio: Profesor {} asignado a {}.", p.getNombre(), idDep);
    }

    // --- LISTADOS (HIDRATACIÓN) ---
    public List<Departamento> obtenerTodoHidratado() throws AppException {
        List<Departamento> lista = repoDep.read();
        for (Departamento d : lista) {
            d.setProfesores(repoProf.buscarPorDepartamento(d.getId()));
        }
        return lista;
    }

    // --- TOP 3 SIN LAMBDAS ---
    public List<Profesor> obtenerTop3Sueldos() throws AppException {
        List<Profesor> todos = new ArrayList<>();
        for (Departamento d : obtenerTodoHidratado()) {
            todos.addAll(d.getProfesores());
        }
        Collections.sort(todos, new Comparator<Profesor>() {
            @Override
            public int compare(Profesor p1, Profesor p2) {
                if (p1.getSalario() < p2.getSalario()) return 1;
                if (p1.getSalario() > p2.getSalario()) return -1;
                return 0;
            }
        });
        return todos.subList(0, Math.min(todos.size(), 3));
    }

    // --- BORRADO ---
    public void eliminarDepartamento(String id) throws AppException {
        repoProf.borrarPorDepartamento(id); // Borrar hijos primero
        repoDep.borrar(id); // Borrar padre después
        logger.warn("Servicio: Eliminado DEP {} y sus profesores.", id);
    }
}
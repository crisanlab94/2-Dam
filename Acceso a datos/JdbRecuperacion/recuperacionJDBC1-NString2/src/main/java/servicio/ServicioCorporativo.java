package servicio;
import java.util.*;
import org.apache.logging.log4j.*;
import modelo.*;
import repositorio.*;
import util.MySqlConector;

public class ServicioCorporativo {
    private static final Logger logger = LogManager.getLogger(ServicioCorporativo.class);
    private RepositorioEmpresa repoEmp;
    private RepositorioEmpleado repoEmpl;

    public ServicioCorporativo() throws AppException {
        MySqlConector con = new MySqlConector();
        this.repoEmp = new RepositorioEmpresa(con);
        this.repoEmpl = new RepositorioEmpleado(con);
    }

    // --- ALTA PADRE (Validación por Nombre único) ---
    public void altaEmpresa(Empresa e) throws AppException {
        for (Empresa aux : repoEmp.getLista()) {
            if (aux.getNombre().equalsIgnoreCase(e.getNombre())) 
                throw new AppException("ERROR: La empresa " + e.getNombre() + " ya existe.");
        }
        repoEmp.guardar(e);
    }

    // --- ALTA HIJO (Validación Padre existe) ---
    public void contratarEmpleado(Empleado emp, int idEmpresa) throws AppException {
        if (repoEmp.buscarPorId(idEmpresa) == null) 
            throw new AppException("ERROR: No se puede contratar para una empresa inexistente.");
        repoEmpl.guardar(emp, idEmpresa);
    }

    // --- LISTADO HIDRATADO ---
    public List<Empresa> obtenerTodasHidratadas() throws AppException {
        List<Empresa> lista = repoEmp.getLista();
        for (Empresa e : lista) {
            e.setPlantilla(repoEmpl.buscarPorEmpresa(e.getId()));
        }
        return lista;
    }

    // --- FILTRO 1: Empresas de un sector con más de X empleados ---
    public List<Empresa> buscarEmpresasGrandesPorSector(Sector s, int minEmpleados) throws AppException {
        List<Empresa> resultado = new ArrayList<>();
        for (Empresa e : obtenerTodasHidratadas()) {
            if (e.getSector() == s && e.getPlantilla().size() >= minEmpleados) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    // --- FILTRO 2: Top 3 Empleados que más cobran (Sin Lambdas) ---
    public List<Empleado> obtenerTop3Sueldos() throws AppException {
        List<Empleado> todos = new ArrayList<>();
        for (Empresa e : obtenerTodasHidratadas()) {
            todos.addAll(e.getPlantilla());
        }
        Collections.sort(todos, new Comparator<Empleado>() {
            @Override
            public int compare(Empleado e1, Empleado e2) {
                if (e1.getSalario() < e2.getSalario()) return 1;
                if (e1.getSalario() > e2.getSalario()) return -1;
                return 0;
            }
        });
        return todos.subList(0, Math.min(todos.size(), 3));
    }

    // --- BORRADO MANUAL (Hijos -> Padre) ---
    public void liquidarEmpresa(int id) throws AppException {
        repoEmpl.borrarPorEmpresa(id);
        repoEmp.borrar(id);
        logger.warn("Servicio: Empresa ID {} eliminada por completo.", id);
    }
}

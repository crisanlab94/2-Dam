package servicio;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.PlanActivo;
import modelo.Usuario;
import repositorio.RepositorioSinAutoincrementar;

/**
 * Clase ServicioUsuarios: Capa de lógica de negocio.
 * Se comunica con el Repositorio para gestionar los datos de los usuarios.
 */
public class ServicioUsuarios {
    private static final Logger logger = LogManager.getLogger(ServicioUsuarios.class);
    private final RepositorioSinAutoincrementar repositorio;

    /**
     * CONSTRUCTOR: Inyecta el repositorio necesario.
     */
    public ServicioUsuarios(RepositorioSinAutoincrementar repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * MÉTODO: registrarUsuario
     * PARA QUÉ SIRVE: Envía un nuevo usuario al repositorio para su guardado físico y en caché.
     */
    public void registrarUsuario(Usuario u) throws AppException {
        logger.info("Solicitando registro de nuevo usuario: " + u.getId());
        this.repositorio.guardar(u);
        return; // Único punto de salida para void
    }

    /**
     * MÉTODO: obtenerTodosLosUsuarios
     * PARA QUÉ SIRVE: Recupera la lista completa de la caché del repositorio.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> lista = this.repositorio.getUsuarios();
        logger.info("Recuperando lista completa de usuarios. Total: " + lista.size());
        return lista;
    }

    /**
     * MÉTODO: buscarPorId
     * PARA QUÉ SIRVE: Busca un usuario por su ID String. 
     * LÓGICA: Si el usuario no existe, lanza una excepción informativa.
     */
    public Usuario buscarPorId(String id) throws AppException {
        Usuario encontrado = this.repositorio.buscarPorId(id);
        
        if (encontrado == null) {
            logger.warn("No se encontró el usuario con ID: " + id);
            throw new AppException("El usuario con ID " + id + " no existe en el sistema.");
        }
        
        return encontrado;
    }

    // --- MÉTODOS DE BÚSQUEDA DELEGADOS AL REPOSITORIO ---

    public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> resultados = this.repositorio.buscarPorNombre(nombre);
        return resultados;
    }

    public List<Usuario> buscarPorNombreQueEmpiezaPorA() {
        List<Usuario> resultados = this.repositorio.buscarPorNombreEmpiezaPorA();
        return resultados;
    }

    public Usuario buscarPorNombreYEmail(String nombre, String email) throws AppException {
        Usuario encontrado = this.repositorio.buscarPorNombreYEmail(nombre, email);
        
        if (encontrado == null) {
            throw new AppException("No existe un usuario con ese nombre y email combinados.");
        }
        
        return encontrado;
    }

    public List<Usuario> buscarPorNombreYPlan(String nombre, PlanActivo plan) {
        List<Usuario> resultados = this.repositorio.buscarPorNombreYPlan(nombre, plan);
        return resultados;
    }

    public List<Usuario> buscarPorNombreEIdioma(String nombre, String idioma) {
        List<Usuario> resultados = this.repositorio.buscarPorNombreEIdioma(nombre, idioma);
        return resultados;
    }

    public Usuario buscarPorLimiteDatosYEmail(boolean tieneLimite, String email) throws AppException {
        Usuario encontrado = this.repositorio.buscarPorLimiteDatosYEmail(tieneLimite, email);
        
        if (encontrado == null) {
            throw new AppException("No se encontró ningún usuario con esos criterios de límite de datos y email.");
        }
        
        return encontrado;
    }
    
    public List<Usuario> listarPorPlanOrdenado(PlanActivo plan) {
        return repositorio.getUsuariosPorPlan(plan);
    }

    public void mostrarEstadisticasVip() {
        int vips = repositorio.contarUsuariosVip();
        logger.info("Número de usuarios con plan VIP: " + vips);
    }

    public List<Usuario> obtenerTop3Android14() {
        return repositorio.buscarComplejoApartado6();
    }

    public void borrarDispositivos(String modelo) throws AppException {
        int total = repositorio.eliminarPorDispositivo(modelo);
        if (total == 0) {
            throw new AppException("No se encontró ningún dispositivo " + modelo + " para eliminar.");
        }
    }
    
    public void masivoMensualATrimestral() throws AppException {
        int total = repositorio.actualizarPlanesMensuales();
        if (total == 0) {
            logger.warn("No había usuarios con plan MENSUAL para actualizar.");
        }
    }

    public List<Usuario> obtenerVipsConInfo() {
        return repositorio.getVipsDetallados();
    }
}
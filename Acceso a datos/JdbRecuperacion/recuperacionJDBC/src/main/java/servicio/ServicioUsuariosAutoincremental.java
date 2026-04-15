package servicio;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException; 
import modelo.PlanActivo;
import modelo.Usuario;
import repositorio.RepositorioConAutoincrementar;


/**
 * Clase ServicioUsuarios: Capa de lógica de negocio para IDs autoincrementales.
 * Se encarga de validar los datos antes de permitir el acceso a la base de datos.
 */
public class ServicioUsuariosAutoincremental {
    private static final Logger logger = LogManager.getLogger(ServicioUsuariosAutoincremental.class);
    private final RepositorioConAutoincrementar repositorio;

    /**
     * CONSTRUCTOR: Recibe la instancia del repositorio.
     */
    public ServicioUsuariosAutoincremental(RepositorioConAutoincrementar repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * MÉTODO: registrarUsuario
     * LÓGICA: En autoincremental, validamos por EMAIL antes de guardar, 
     * ya que el ID lo generará MySQL automáticamente durante la inserción. 
     */
    public void registrarUsuario(Usuario u) throws AppException {
        // Buscamos en la caché si ya existe un usuario con ese email
        if (this.repositorio.buscarPorEmail(u.getEmail()) != null) {
            logger.error("Intento de registro con email duplicado: " + u.getEmail());
            throw new AppException("Error de negocio: El email " + u.getEmail() + " ya está registrado.");
        }
        
        // Si el email está libre, procedemos al guardado físico
        this.repositorio.save(u);
        logger.info("Usuario registrado con éxito. MySQL ha generado su ID.");
        
        return; // Único punto de salida para void
    }

    /**
     * MÉTODO: buscarPorId
     * PARA QUÉ SIRVE: Busca un usuario por su ID numérico (int).
     * LÓGICA: Si el repositorio devuelve null, el servicio lanza la excepción.
     */
    public Usuario buscarPorId(int id) throws AppException {
        Usuario encontrado = this.repositorio.buscarPorId(id);
        
        if (encontrado == null) {
            logger.warn("Búsqueda fallida para el ID numérico: " + id);
            throw new AppException("No se ha encontrado ningún usuario con el ID: " + id);
        }
        
        return encontrado; // Único return
    }

    /**
     * MÉTODO: obtenerTodosLosUsuarios
     * PARA QUÉ SIRVE: Retorna la lista completa almacenada en la caché.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> listaTotal = this.repositorio.getUsuarios();
        return listaTotal;
    }

    // --- MÉTODOS DE FILTRADO (DELEGADOS AL REPOSITORIO) ---

    public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> resultados = this.repositorio.buscarPorNombre(nombre);
        return resultados;
    }

    public List<Usuario> buscarPorNombreQueEmpiezaPorA() {
        List<Usuario> resultados = this.repositorio.buscarPorNombreEmpiezaPorA();
        return resultados;
    }

    /**
     * MÉTODO: buscarPorNombreYEmail
     * LÓGICA: Combinación exacta de dos campos únicos.
     */
    public Usuario buscarPorNombreYEmail(String nombre, String email) throws AppException {
        Usuario encontrado = this.repositorio.buscarPorNombreYEmail(nombre, email);
        
        if (encontrado == null) {
            throw new AppException("No existe coincidencia para el nombre y email proporcionados.");
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
            throw new AppException("Criterios de búsqueda no encontrados (Límite datos + Email).");
        }
        
        return encontrado;
    }
}
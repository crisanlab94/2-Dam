package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.PlanActivo;
import modelo.Preferencias;
import modelo.Usuario;
import util.MySqlConector;


/**
 * Clase RepositorioUsuarios:
 * Gestiona la persistencia en la base de datos 'usuariosApp' y mantiene una caché en memoria.
 */
public class RepositorioSinAutoincrementar {
    private static final Logger logger = LogManager.getLogger(RepositorioSinAutoincrementar.class);
    private final MySqlConector mysqlConector;
    
    // Atributo Caché: Lista en memoria que refleja el estado de la base de datos 
    private List<Usuario> usuarios; 

    /**
     * CONSTRUCTOR:
     * Inicializa el conector y carga la lista 'usuarios' desde la base de datos al arrancar.
     */
    public RepositorioSinAutoincrementar(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.usuarios = this.read(); 
    }

    /**
     * MÉTODO: mapearResultSetAObjeto
     * PARA QUÉ SIRVE: Centraliza la lógica de "traducción" de SQL a Java para no repetir código.
     * QUÉ HACE: Extrae cada columna del ResultSet y construye un objeto Usuario completo.
     * REGLA: Un solo return al final.
     */
    private Usuario mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        
        // El ID se recupera como String según tu script original [cite: 5]
        u.setId(rs.getString("id")); 
        u.setUsername(rs.getString("username"));
        u.setEmail(rs.getString("email"));
        
        // Conversión del Enum PlanActivo [cite: 2]
        u.setPlan_activo(PlanActivo.valueOf(rs.getString("plan_activo")));
        u.setDispositivo(rs.getString("dispositivo"));

        // Se cargan las preferencias para que el objeto no llegue a null (Relación 1 a 1) [cite: 3]
        Preferencias p = new Preferencias();
        p.setTema_oscuro(rs.getBoolean("tema_oscuro"));
        p.setIdioma(rs.getString("idioma"));
        p.setNotificaciones_push(rs.getBoolean("notificaciones_push"));
        p.setLimite_datos_moviles(rs.getBoolean("limite_datos_moviles"));
        u.setPreferencias(p);
        
        return u;
    }

    /**
     * MÉTODO: read
     * PARA QUÉ SIRVE: Realiza una lectura completa de la base de datos.
     * QUÉ PONE: SQL con LEFT JOIN para traer los datos de las dos tablas a la vez.
     * REGLA: Un solo return al final.
     */
    public List<Usuario> read() throws AppException {
        List<Usuario> listaBD = new ArrayList<>();
        // El JOIN es clave para no perder la información de las preferencias 
        String sql = "SELECT u.*, p.tema_oscuro, p.idioma, p.notificaciones_push, p.limite_datos_moviles " +
                     "FROM usuarios u LEFT JOIN preferencias p ON u.id = p.usuario_id";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // Añadimos cada usuario mapeado a la lista temporal
                listaBD.add(mapearResultSetAObjeto(rs));
            }
            logger.info("Caché cargada con éxito.");
        } catch (SQLException e) {
            throw new AppException("Error al leer datos de la base de datos: " + e.getMessage());
        } finally {
            mysqlConector.release(); // Siempre liberamos la conexión [cite: 5]
        }
        return listaBD;
    }

    /**
     * MÉTODO: save (TRANSACCIÓN CON ID STRING)
     * PARA QUÉ SIRVE: Registra un usuario nuevo en las dos tablas.
     * QUÉ HACE: 
     * 1. Valida que el ID no esté repetido.
     * 2. Usa setAutoCommit(false) para garantizar que si falla un INSERT, no se guarde nada.
     * 3. Si todo va bien, actualiza la lista 'usuarios' (caché).
     * REGLA: Único return al final del método (aunque sea void).
     */
    public void guardar(Usuario usuario) throws AppException {
        // Validación: El ID debe ser único 
        if (this.buscarPorId(usuario.getId()) != null) {
            throw new AppException("No se puede registrar: El usuario con ID " + usuario.getId() + " ya existe.");
        }

        String sqlUser = "INSERT INTO usuarios (id, username, email, plan_activo, dispositivo) VALUES (?,?,?,?,?)";
        String sqlPref = "INSERT INTO preferencias (usuario_id, tema_oscuro, idioma, notificaciones_push, limite_datos_moviles) VALUES (?,?,?,?,?)";

        try (Connection conn = mysqlConector.getConnect()) {
            conn.setAutoCommit(false); // Desactivamos el guardado automático para usar transacción 

            try (PreparedStatement psU = conn.prepareStatement(sqlUser);
                 PreparedStatement psP = conn.prepareStatement(sqlPref)) {

                // Parámetros tabla Usuarios
                psU.setString(1, usuario.getId());
                psU.setString(2, usuario.getUsername());
                psU.setString(3, usuario.getEmail());
                psU.setString(4, usuario.getPlan_activo().name()); // .name() para el Enum 
                psU.setString(5, usuario.getDispositivo());
                psU.executeUpdate();

                // Parámetros tabla Preferencias (Vinculada por el mismo ID String)
                psP.setString(1, usuario.getId());
                psP.setBoolean(2, usuario.getPreferencias().isTema_oscuro());
                psP.setString(3, usuario.getPreferencias().getIdioma());
                psP.setBoolean(4, usuario.getPreferencias().isNotificaciones_push());
                psP.setBoolean(5, usuario.getPreferencias().isLimite_datos_moviles());
                psP.executeUpdate();

                // Si no hay errores, confirmamos en la base de datos
                conn.commit();
                
                // Actualizamos la caché en memoria para que esté sincronizada
                this.usuarios.add(usuario);
                logger.info("Guardado físico y en caché realizado.");

            } catch (SQLException e) {
                // Si algo falla, deshacemos cualquier cambio parcial
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new AppException("Error crítico durante el guardado: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        
        return; // Único punto de salida para el método void
    }

    /**
     * MÉTODO: buscarPorId
     * PARA QUÉ SIRVE: Localiza un usuario rápidamente consultando la caché.
     * REGLA: Variable local 'encontrado' para asegurar un solo return.
     */
    public Usuario buscarPorId(String id) {
        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(id)) {
                encontrado = u;
            }
        }
        return encontrado;
    }

    /**
     * GETTER: Devuelve la lista que está en memoria.
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
    /**
     * 1. BÚSQUEDA POR NOMBRE (Contiene)
     * PARA QUÉ SIRVE: Devuelve una lista de usuarios cuyo nombre contiene el texto buscado.
     */
    public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> resultados = new ArrayList<>();
        for (Usuario u : usuarios) {
            // Usamos contains para que sea una búsqueda flexible
            if (u.getUsername().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(u);
            }
        }
        return resultados;
    }

    /**
     * 2. BÚSQUEDA POR NOMBRE QUE COMIENZA POR 'A'
     * PARA QUÉ SIRVE: Filtra usuarios cuyo nombre empieza específicamente por la letra 'A'.
     */
    public List<Usuario> buscarPorNombreEmpiezaPorA() {
        List<Usuario> resultados = new ArrayList<>();
        for (Usuario u : usuarios) {
            // startsWith comprueba el inicio de la cadena
            if (u.getUsername().toUpperCase().startsWith("A")) {
                resultados.add(u);
            }
        }
        return resultados;
    }

    /**
     * 3. BÚSQUEDA POR NOMBRE Y EMAIL (Exacta)
     * PARA QUÉ SIRVE: Busca un usuario específico que coincida con ambos campos.
     * RETORNO: Un solo Usuario, ya que la combinación suele ser única.
     */
    public Usuario buscarPorNombreYEmail(String nombre, String email) {
        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            if (u.getUsername().equalsIgnoreCase(nombre) && u.getEmail().equalsIgnoreCase(email)) {
                encontrado = u;
            }
        }
        return encontrado;
    }

    /**
     * 4. BÚSQUEDA POR NOMBRE Y PLAN ACTIVO
     * PARA QUÉ SIRVE: Filtra usuarios por nombre y su categoría de plan (FREE, VIP, etc.).
     */
    public List<Usuario> buscarPorNombreYPlan(String nombre, PlanActivo plan) {
        List<Usuario> resultados = new ArrayList<>();
        for (Usuario u : usuarios) {
            // Comparamos el String del nombre y el Enum del plan [cite: 2, 3]
            if (u.getUsername().toLowerCase().contains(nombre.toLowerCase()) && u.getPlan_activo() == plan) {
                resultados.add(u);
            }
        }
        return resultados;
    }

    /**
     * 5. BÚSQUEDA POR NOMBRE E IDIOMA (Preferencia)
     * PARA QUÉ SIRVE: Accede al objeto interno 'Preferencias' para filtrar. [cite: 4]
     */
    public List<Usuario> buscarPorNombreEIdioma(String nombre, String idioma) {
        List<Usuario> resultados = new ArrayList<>();
        for (Usuario u : usuarios) {
            // Accedemos a u.getPreferencias() para llegar al idioma 
            if (u.getUsername().toLowerCase().contains(nombre.toLowerCase()) && 
                u.getPreferencias().getIdioma().equalsIgnoreCase(idioma)) {
                resultados.add(u);
            }
        }
        return resultados;
    }

    /**
     * 6. BÚSQUEDA POR LÍMITE DE DATOS MÓVILES (Preferencia) Y EMAIL
     * PARA QUÉ SIRVE: Busca usuarios con una configuración booleana específica y un email concreto.
     */
    public Usuario buscarPorLimiteDatosYEmail(boolean tieneLimite, String email) {
        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            // Filtramos por el boolean de preferencias y el String del email 
            if (u.getPreferencias().isLimite_datos_moviles() == tieneLimite && 
                u.getEmail().equalsIgnoreCase(email)) {
                encontrado = u;
            }
        }
        return encontrado;
    }
    
    /**
     * APARTADO 3: Filtrar por plan y ordenar por Email ASC.
     */
    public List<Usuario> getUsuariosPorPlan(PlanActivo plan) {
        List<Usuario> resultados = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getPlan_activo() == plan) {
                resultados.add(u);
            }
        }
        // Ordenación por email (Requisito de la rúbrica)
        resultados.sort((u1, u2) -> u1.getEmail().compareToIgnoreCase(u2.getEmail()));
        return resultados;
    }

    /**
     * APARTADO 5: Contar usuarios VIP.
     */
    public int contarUsuariosVip() {
        int contador = 0;
        for (Usuario u : usuarios) {
            if (u.getPlan_activo() == PlanActivo.VIP) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * APARTADO 6: Filtro complejo.
     * (Plan MENSUAL O Notif=false) Y dispositivo=android14. 
     * Ordenado por PlanActivo y limitado a los 3 primeros.
     */
    public List<Usuario> buscarComplejoApartado6() {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : usuarios) {
            boolean cumplePlanONotif = (u.getPlan_activo() == PlanActivo.MENSUAL || !u.getPreferencias().isNotificaciones_push());
            boolean cumpleDispo = u.getDispositivo().equalsIgnoreCase("android14");
            
            if (cumplePlanONotif && cumpleDispo) {
                filtrados.add(u);
            }
        }
        // Ordenar por PlanActivo (ASC)
        filtrados.sort((u1, u2) -> u1.getPlan_activo().compareTo(u2.getPlan_activo()));
        
        // Devolver solo los 3 primeros
        List<Usuario> top3 = new ArrayList<>();
        int limite = Math.min(filtrados.size(), 3);
        for(int i = 0; i < limite; i++) {
            top3.add(filtrados.get(i));
        }
        return top3;
    }

    /**
     * APARTADO 7: Eliminar por dispositivo (Corregido para evitar error de FK)
     * LÓGICA: Usamos una transacción para borrar de ambas tablas en orden.
     */
    public int eliminarPorDispositivo(String nombreDispos) throws AppException {
        int filasBorradas = 0;
        // SQL para borrar las preferencias de los usuarios que tengan ese dispositivo
        String sqlPref = "DELETE p FROM preferencias p JOIN usuarios u ON p.usuario_id = u.id WHERE u.dispositivo = ?";
        // SQL para borrar a los usuarios
        String sqlUser = "DELETE FROM usuarios WHERE dispositivo = ?";

        try (Connection conn = mysqlConector.getConnect()) {
            conn.setAutoCommit(false); // Iniciamos transacción

            try (PreparedStatement stP = conn.prepareStatement(sqlPref);
                 PreparedStatement stU = conn.prepareStatement(sqlUser)) {

                // 1. Borramos de la tabla hija (preferencias)
                stP.setString(1, nombreDispos);
                stP.executeUpdate();

                // 2. Borramos de la tabla padre (usuarios)
                stU.setString(1, nombreDispos);
                filasBorradas = stU.executeUpdate();

                // 3. Si todo va bien, confirmamos cambios
                conn.commit();
                
                // 4. Sincronizamos la caché en memoria
                usuarios.removeIf(u -> u.getDispositivo().equalsIgnoreCase(nombreDispos));
                logger.info("Apartado 7: Eliminados " + filasBorradas + " usuarios con dispositivo " + nombreDispos);

            } catch (SQLException e) {
                conn.rollback(); // Si algo falla, deshacemos todo para no dejar datos corruptos
                throw e;
            }
        } catch (SQLException e) {
            throw new AppException("Error crítico al eliminar: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return filasBorradas; // Un solo return
    }
    
    /**
     * MÉTODO: actualizarPlanesMensuales
     * QUÉ HACE: Cambia en la BD todos los 'MENSUAL' a 'TRIMESTRAL' 
     * y actualiza la lista en memoria (caché).
     */
    public int actualizarPlanesMensuales() throws AppException {
        int filasAfectadas = 0;
        String sql = "UPDATE usuarios SET plan_activo = 'TRIMESTRAL' WHERE plan_activo = 'MENSUAL'";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            filasAfectadas = stmt.executeUpdate();

            // SINCRONIZACIÓN: Actualizamos la caché para que el cambio se vea sin reiniciar
            for (Usuario u : usuarios) {
                if (u.getPlan_activo() == PlanActivo.MENSUAL) {
                    u.setPlan_activo(PlanActivo.TRIMESTRAL);
                }
            }
            logger.info("Actualización masiva: " + filasAfectadas + " usuarios pasados a TRIMESTRAL.");

        } catch (SQLException e) {
            throw new AppException("Error técnico al actualizar planes: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return filasAfectadas; // Un solo return como pide tu regla
    }

    /**
     * MÉTODO: getVipsDetallados
     * QUÉ HACE: Filtra la caché para devolver solo los usuarios VIP con toda su info.
     */
    public List<Usuario> getVipsDetallados() {
        List<Usuario> listaVips = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getPlan_activo() == PlanActivo.VIP) {
                listaVips.add(u);
            }
        }
        return listaVips;
    }
}
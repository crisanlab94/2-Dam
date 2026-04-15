package repositorio;

import java.sql.*;
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
 * Gestiona las operaciones CRUD en la base de datos y mantiene una caché local.
 */
public class RepositorioConAutoincrementar {
    private static final Logger logger = LogManager.getLogger(RepositorioConAutoincrementar.class);
    private final MySqlConector mysqlConector;
    
    // Lista en memoria (caché) para evitar consultas constantes a la BD. 
    private List<Usuario> usuarios; 

    /**
     * CONSTRUCTOR:
     * Inicializa el conector y carga los datos existentes de la BD a la caché mediante read().
     */
    public RepositorioConAutoincrementar(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.usuarios = this.read(); 
    }

    /**
     * MÉTODO: mapearResultSetAObjeto
     * PARA QUÉ SIRVE: Convierte una fila del ResultSet de SQL en un objeto Usuario de Java.
     * QUÉ SE PONE: 
     * - Asignación de columnas SQL a atributos Java (rs.getInt, rs.getString, rs.getBoolean).
     * - Creación del objeto Preferencias para asegurar que no sea null en el Usuario. 
     */
    private Usuario mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        // El ID ahora se recupera como int debido al cambio a AUTO_INCREMENT. 
        u.setId(rs.getInt("id")); 
        u.setUsername(rs.getString("username"));
        u.setEmail(rs.getString("email"));
        
        // El Enum PlanActivo se obtiene convirtiendo el String almacenado en la BD. [cite: 2]
        u.setPlan_activo(PlanActivo.valueOf(rs.getString("plan_activo")));
        u.setDispositivo(rs.getString("dispositivo"));

        // Se inicializan las preferencias para cumplir con la carga de relaciones. [cite: 3]
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
     * PARA QUÉ SIRVE: Recupera todos los usuarios con sus preferencias usando un JOIN.
     * USO: Se llama al inicio para llenar la caché y cuando se requiere refrescar datos.
     */
    public List<Usuario> read() throws AppException {
        List<Usuario> listaBD = new ArrayList<>();
        // LEFT JOIN para garantizar que se traen los usuarios aunque no tengan preferencias. 
        String sql = "SELECT u.*, p.tema_oscuro, p.idioma, p.notificaciones_push, p.limite_datos_moviles " +
                     "FROM usuarios u LEFT JOIN preferencias p ON u.id = p.usuario_id";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listaBD.add(mapearResultSetAObjeto(rs));
            }
            logger.info("Datos cargados en la caché correctamente.");
        } catch (SQLException e) {
            throw new AppException("Error al sincronizar la base de datos con la caché: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return listaBD;
    }

    /**
     * MÉTODO: save (VERSION AUTO_INCREMENT CON TRANSACCIÓN)
     * PARA QUÉ SIRVE: Inserta un nuevo registro en dos tablas de forma atómica.
     * USO:
     * - Se omite el 'id' en el primer INSERT porque la BD lo genera solo.
     * - Se recupera el ID generado para vincular las preferencias. 
     * - Se usa commit/rollback para integridad de datos. 
     */
    public void save(Usuario usuario) throws AppException {
        // Validación: Se comprueba por email que el usuario no esté duplicado.
        if (this.buscarPorEmail(usuario.getEmail()) != null) {
            throw new AppException("No se puede registrar: El email ya existe.");
        }

        String sqlUser = "INSERT INTO usuarios (username, email, plan_activo, dispositivo) VALUES (?,?,?,?)";
        String sqlPref = "INSERT INTO preferencias (usuario_id, tema_oscuro, idioma, notificaciones_push, limite_datos_moviles) VALUES (?,?,?,?,?)";

        try (Connection conn = mysqlConector.getConnect()) {
            conn.setAutoCommit(false); // Inicio de transacción. 

            // Statement.RETURN_GENERATED_KEYS permite recuperar el ID creado por MySQL. 
            try (PreparedStatement psU = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psP = conn.prepareStatement(sqlPref)) {

                psU.setString(1, usuario.getUsername());
                psU.setString(2, usuario.getEmail());
                psU.setString(3, usuario.getPlan_activo().name());
                psU.setString(4, usuario.getDispositivo());
                psU.executeUpdate();

                // Recuperación del ID generado automáticamente. 
                try (ResultSet rsKeys = psU.getGeneratedKeys()) {
                    if (rsKeys.next()) {
                        usuario.setId(rsKeys.getInt(1));
                    }
                }

                // Inserción en tabla secundaria usando el ID recién recuperado.
                psP.setInt(1, usuario.getId());
                psP.setBoolean(2, usuario.getPreferencias().isTema_oscuro());
                psP.setString(3, usuario.getPreferencias().getIdioma());
                psP.setBoolean(4, usuario.getPreferencias().isNotificaciones_push());
                psP.setBoolean(5, usuario.getPreferencias().isLimite_datos_moviles());
                psP.executeUpdate();

                conn.commit(); // Confirmación de los cambios. 
                this.usuarios.add(usuario); // Actualización de la caché local. 
            } catch (SQLException e) {
                conn.rollback(); // Cancelación en caso de error. 
                throw e;
            }
        } catch (SQLException e) {
            throw new AppException("Error al guardar el usuario y preferencias: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
    }

    /**
     * MÉTODO: buscarPorId
     * PARA QUÉ SIRVE: Localiza un usuario en la caché mediante su ID numérico.
     * LÓGICA: Se utiliza una variable 'encontrado' para asegurar un único return.
     */
    public Usuario buscarPorId(int id) {
        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                encontrado = u;
            }
        }
        return encontrado;
    }

    /**
     * MÉTODO: buscarPorEmail
     * PARA QUÉ SIRVE: Verifica si un email ya está en uso.
     * LÓGICA: Se utiliza una variable 'encontrado' para asegurar un único return.
     */
    public Usuario buscarPorEmail(String email) {
        Usuario encontrado = null;
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                encontrado = u;
            }
        }
        return encontrado;
    }

    /**
     * GETTER: Devuelve la lista en memoria (caché).
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
}
package repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // Para manejar DATETIME
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.EstadoMision;
import modelo.Mision;
import modelo.RangoAstronauta;
import util.MySqlConector;

public class RepositorioMision {
    private static final Logger logger = LogManager.getLogger(RepositorioMision.class);
    private final MySqlConector mysqlConector;
    private List<Mision> misiones; // Nuestra caché en memoria

    public RepositorioMision(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        // Al arrancar el repositorio, cargamos los datos de la BD a la lista
        this.misiones = this.read(); 
    }

    /**
     * Convierte una fila de la base de datos en un objeto Mision de Java.
     */
    private Mision mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Mision m = new Mision();
        
        // Asignamos datos básicos de la misión
        m.setId(rs.getInt("id_mision")); 
        m.setNombreNave(rs.getString("nombre_nave"));
        
        // Conversión: java.sql.Date -> java.time.LocalDate
        if (rs.getDate("fecha_lanzamiento") != null) {
            m.setFechaLanzamiento(rs.getDate("fecha_lanzamiento").toLocalDate());
        }
        
        // Conversión: java.sql.Timestamp -> java.time.LocalDateTime
        Timestamp ts = rs.getTimestamp("ultima_conexion");
        if (ts != null) {
            m.setUltimaConexion(ts.toLocalDateTime());
        }
        
        // Conversión de Enum y Booleano
        m.setEstado(EstadoMision.valueOf(rs.getString("estado")));
        m.setCombustibleExtra(rs.getBoolean("combustible_extra"));

        // HIDRATACIÓN: Creamos el objeto Astronauta completo usando los datos del JOIN
        Astronauta a = new Astronauta();
        a.setId(rs.getInt("id_astronauta")); 
        a.setNombre(rs.getString("nombre"));  
        a.setRango(RangoAstronauta.valueOf(rs.getString("rango")));
        a.setHorasVuelo(rs.getInt("horas_vuelo"));
        a.setActivo(rs.getBoolean("activo"));
        
        // Metemos el astronauta dentro de la misión
        m.setAstronauta(a); 
        
        return m;
    }
    
    /**
     * Lee todas las misiones realizando un JOIN con la tabla de Astronautas.
     */
    public List<Mision> read() throws AppException {
        List<Mision> listaBD = new ArrayList<>();
        
        // SQL con JOIN para traer los datos del piloto en la misma consulta
        String sql = "SELECT m.id AS id_mision, m.id_astronauta, m.nombre_nave, m.fecha_lanzamiento, m.ultima_conexion, m.combustible_extra, m.estado, " +
                     "a.nombre, a.rango, a.horas_vuelo, a.activo " +
                     "FROM sandovalcristinaMision m " +
                     "LEFT JOIN sandovalcristinaAstronauta a ON m.id_astronauta = a.id";

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
     * Guarda una misión validando que el astronauta no supere el límite de 3.
     */
    public void guardar(Mision mision) throws AppException {
        // 1. VALIDACIÓN (Bucle tradicional): Contamos misiones de este astronauta
        int contadorMisiones = 0;
        for (Mision m : this.misiones) {
            if (m.getAstronauta().getId() == mision.getAstronauta().getId()) {
                contadorMisiones++;
            }
        }

        // Si ya tiene 3 misiones, bloqueamos la operación
        if (contadorMisiones >= 3) {
            throw new AppException("Límite alcanzado: El astronauta " + 
                                   mision.getAstronauta().getNombre() + 
                                   " ya tiene 3 misiones asignadas.");
        }

        // 2. SINCRONIZACIÓN DE FECHA: Si no tiene fecha de conexión, le ponemos "ahora"
        // Esto evita el error de NullPointerException y mantiene la caché igual que la BD
        if (mision.getUltimaConexion() == null) {
            mision.setUltimaConexion(LocalDateTime.now());
            logger.info("Sincronización: Se ha asignado la hora actual a la misión.");
        }

        // 3. OPERACIÓN FÍSICA EN BASE DE DATOS
        String sql = "INSERT INTO sandovalcristinaMision (id_astronauta, nombre_nave, fecha_lanzamiento, " +
                     "ultima_conexion, combustible_extra, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, mision.getAstronauta().getId());
            stmt.setString(2, mision.getNombreNave());
            
            // LocalDate -> java.sql.Date
            stmt.setDate(3, Date.valueOf(mision.getFechaLanzamiento()));
            
            // LocalDateTime -> java.sql.Timestamp (Aquí ya es seguro porque controlamos el null arriba)
            stmt.setTimestamp(4, Timestamp.valueOf(mision.getUltimaConexion()));
            
            stmt.setBoolean(5, mision.isCombustibleExtra());
            stmt.setString(6, mision.getEstado().name());

            stmt.executeUpdate();
            
            // 4. ACTUALIZACIÓN DE CACHÉ: Añadimos el objeto a la lista de memoria
            this.misiones.add(mision);
            logger.info("Mision registrada con éxito. Total en memoria: " + misiones.size());

        } catch (SQLException e) {
            throw new AppException("Error al insertar la mision en la BD: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
    }

    public List<Mision> getMisiones() {
        return misiones;
    }
    
    
}
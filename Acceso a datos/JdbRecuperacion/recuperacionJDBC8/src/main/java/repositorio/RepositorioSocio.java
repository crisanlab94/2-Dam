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
import modelo.Socio;
import modelo.TipoCuota;
import util.MySqlConector;

public class RepositorioSocio {
	private static final Logger logger = LogManager.getLogger(RepositorioSocio.class);
    private final MySqlConector mysqlConector;
    private List<Socio> socios; 
    
    public RepositorioSocio(MySqlConector mysqlConector) throws AppException {
        this.mysqlConector = mysqlConector;
        this.socios = this.read(); 
    }

    private Socio mapearResultSetAObjeto(ResultSet rs) throws SQLException {
        Socio s = new Socio();
        s.setId(rs.getInt("id_socio")); 
        s.setNombre(rs.getString("nombre"));
        s.setEmail(rs.getString("email"));
        s.setTipoCuota(TipoCuota.valueOf(rs.getString("tipo_cuota")));
        s.setEstaActivo(rs.getBoolean("esta_activo"));
        return s;
    }

    public List<Socio> read() throws AppException {
        List<Socio> listaBD = new ArrayList<>();
        String sql = "SELECT * FROM SandovalCristinaSocio";

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
    
    public void guardar(Socio Socio) throws AppException {
        if (this.buscarPorId(Socio.getId()) != null) {
            throw new AppException("No se puede registrar: El socio con ID " + Socio.getId() + " ya existe.");
        }

        String sqlAst = "INSERT INTO SandovalCristinaSocio (id_socio, nombre, email, tipo_cuota, esta_activo) VALUES (?,?,?, ?, ?)";

        try (Connection conn = mysqlConector.getConnect()) {
            conn.setAutoCommit(false); 

            try (PreparedStatement psS = conn.prepareStatement(sqlAst)) {
                psS.setInt(1, Socio.getId());
                psS.setString(2, Socio.getNombre());
                psS.setString(3, Socio.getEmail());
                psS.setString(4, Socio.getTipoCuota().name());
                psS.setBoolean(5, Socio.isEstaActivo());
                
                psS.executeUpdate();

                conn.commit();
                this.socios.add(Socio);
                logger.info("Guardado físico y en caché realizado.");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new AppException("Error crítico durante el guardado: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
    }
    
    public Socio buscarPorId(int id) {
        Socio encontrado = null;
      
        for (Socio s : socios) {
            if (s.getId() == id) {
                encontrado = s;
                
            }
        }
        
        return encontrado;
    }
    
    public List<Socio> getSocio() {
        return socios;
    }
    
    public List<Socio> getSociosPorCuota(TipoCuota cuota) throws AppException {
        List<Socio> lista = new ArrayList<Socio>();
        String sql = "SELECT * FROM SandovalCristinaSocio WHERE tipo_cuota = ? ORDER BY nombre ASC";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, cuota.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearResultSetAObjeto(rs));
                }
            }
        } catch (SQLException e) {
            throw new AppException("Error al filtrar por tipo cuota: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return lista;
    }
    
 // APARTADO 5: Contar socios activos con COUNT en SQL
    public int contarSociosActivos() throws AppException {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM SandovalCristinaSocio WHERE esta_activo = true";

        try (Connection conn = mysqlConector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new AppException("Error al contar socios: " + e.getMessage());
        } finally {
            mysqlConector.release();
        }
        return total; 
    }
}

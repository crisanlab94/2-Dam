package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.AppException;
import modelo.Empleado;
import util.MySqlConector;

public class RepositorioEmpleado {
    private static final Logger logger = LogManager.getLogger(RepositorioEmpleado.class);
    private MySqlConector con;
    private List<Empleado> cache; // La lista en memoria

    public RepositorioEmpleado(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read(); // Carga inicial
    }

    private Empleado mapear(ResultSet rs) throws SQLException {
        return new Empleado(
            rs.getInt("id"), 
            rs.getString("nombre"), 
            rs.getDouble("salario"), 
            rs.getBoolean("es_remoto")
        );
    }

    // --- EL MÉTODO READ (QUE FALTABA) ---
    public List<Empleado> read() throws AppException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection c = con.getConnect(); 
             PreparedStatement ps = c.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) { 
            throw new AppException("Error SQL Read Empleados: " + e.getMessage()); 
        }
        return lista;
    }

    // --- BUSCAR POR ID (SQL) ---
    public Empleado buscarPorId(int id) throws AppException {
        Empleado emp = null;
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                emp = mapear(rs);
            }
        } catch (SQLException e) { 
            throw new AppException("Error SQL Buscar Empleado por ID"); 
        }
        return emp;
    }

    public List<Empleado> buscarPorEmpresa(int idEmpresa) throws AppException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE id_empresa = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) { 
            throw new AppException("Error SQL Buscar Empleados por Empresa"); 
        }
        return lista;
    }

    public void guardar(Empleado e, int idEmpresa) throws AppException {
        String sql = "INSERT INTO empleados (nombre, salario, es_remoto, id_empresa) VALUES (?,?,?,?)";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNombre()); 
            ps.setDouble(2, e.getSalario());
            ps.setBoolean(3, e.isEsRemoto()); 
            ps.setInt(4, idEmpresa);
            
            ps.executeUpdate();
            this.cache = this.read(); // Sincronizamos la caché después de insertar
        } catch (SQLException ex) { 
            throw new AppException("Error SQL Guardar Empleado"); 
        }
    }

    public void borrarPorEmpresa(int idEmpresa) throws AppException {
        String sql = "DELETE FROM empleados WHERE id_empresa = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa); 
            ps.executeUpdate();
            this.cache = this.read(); // Sincronizamos la caché después de borrar
        } catch (SQLException ex) { 
            throw new AppException("Error SQL Borrar Empleados por Empresa"); 
        }
    }

    // --- MÉTODO GETLISTA (PARA EL SERVICIO) ---
    public List<Empleado> getLista() {
        return cache;
    }
}

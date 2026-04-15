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
import modelo.Especialidad;
import modelo.Profesor;
import util.MySqlConector;

public class RepositorioProfesor {
    private static final Logger logger = LogManager.getLogger(RepositorioProfesor.class);
    private MySqlConector con;
    private List<Profesor> cache; // Lista en memoria para rapidez

    public RepositorioProfesor(MySqlConector con) throws AppException {
        this.con = con;
        this.cache = this.read(); // Carga inicial al arrancar
    }

    private Profesor mapear(ResultSet rs) throws SQLException {
        return new Profesor(
            rs.getString("dni"), 
            rs.getString("nombre"),
            Especialidad.valueOf(rs.getString("especialidad")), 
            rs.getDouble("salario")
        );
    }

    /**
     * Carga todos los profesores de la base de datos.
     */
    public List<Profesor> read() throws AppException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        try (Connection c = con.getConnect(); 
             PreparedStatement ps = c.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) { 
            throw new AppException("Error SQL Read Profesores: " + e.getMessage()); 
        }
        return lista;
    }

    /**
     * Busca profesores pertenecientes a un departamento específico (SQL).
     */
    public List<Profesor> buscarPorDepartamento(String idDep) throws AppException {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesores WHERE id_departamento = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idDep);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) { 
            throw new AppException("Error SQL Buscar Profesores por Dept: " + e.getMessage()); 
        }
        return lista;
    }

    public void guardar(Profesor p, String idDep) throws AppException {
        String sql = "INSERT INTO profesores (dni, nombre, especialidad, salario, id_departamento) VALUES (?,?,?,?,?)";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getDni()); 
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getEspecialidad().name()); 
            ps.setDouble(4, p.getSalario());
            ps.setString(5, idDep); 
            
            ps.executeUpdate();
            this.cache = this.read(); // Sincronizamos caché
        } catch (SQLException e) { 
            throw new AppException("Error SQL Guardar Profesor: " + e.getMessage()); 
        }
    }

    public Profesor buscarPorDni(String dni) throws AppException {
        Profesor p = null;
        String sql = "SELECT * FROM profesores WHERE dni = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = mapear(rs);
            }
        } catch (SQLException e) { 
            throw new AppException("Error SQL Buscar Profesor por DNI: " + e.getMessage()); 
        }
        return p;
    }

    /**
     * Borra todos los profesores de un departamento (Útil para borrado manual sin CASCADE).
     */
    public void borrarPorDepartamento(String idDep) throws AppException {
        String sql = "DELETE FROM profesores WHERE id_departamento = ?";
        try (Connection c = con.getConnect(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idDep); 
            ps.executeUpdate();
            this.cache = this.read(); // Sincronizamos caché
        } catch (SQLException e) { 
            throw new AppException("Error SQL Borrar Profesores por Dept: " + e.getMessage()); 
        }
    }

    /**
     * Devuelve la lista en memoria (Caché).
     */
    public List<Profesor> getLista() {
        return cache;
    }
}
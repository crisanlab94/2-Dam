package repositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.Seguridad;
import util.MySqlConector;

public class RepositorioAstronauta {
    private static final Logger logger = LogManager.getLogger(RepositorioAstronauta.class);
    private final MySqlConector conector;
    private List<Astronauta> cache;

    public RepositorioAstronauta(MySqlConector conector) throws AppException {
        this.conector = conector;
        this.cache = this.read();
    }

    /**
     * READ: Carga inicial de datos con JOIN
     */
    public List<Astronauta> read() throws AppException {
        List<Astronauta> lista = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre, s.pin_acceso, s.nivel_acceso " + 
                     "FROM sandovalcristinaAstronauta a " +
                     "INNER JOIN sandovalcristinaSeguridad s ON a.id = s.id_astronauta";

        try (Connection conn = conector.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Seguridad seg = new Seguridad(rs.getInt("pin_acceso"), rs.getString("nivel_acceso"));
                Astronauta astro = new Astronauta(rs.getInt("id"), rs.getString("nombre"), seg);
                lista.add(astro);
            }
        } catch (SQLException e) {
            throw new AppException("Error en lectura 1:1: " + e.getMessage());
        } finally {
            conector.release();
        }
        return lista;
    }

    /**
     * GUARDAR: Transacción para ambas tablas
     */
    public void guardar(Astronauta a) throws AppException {
        String sqlAstro = "INSERT INTO sandovalcristinaAstronauta (id, nombre) VALUES (?, ?)";
        String sqlSeg = "INSERT INTO sandovalcristinaSeguridad (id_astronauta, pin_acceso, nivel_acceso) VALUES (?, ?, ?)";

        Connection conn = null;
        try {
            conn = conector.getConnect();
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(sqlAstro)) {
                ps1.setInt(1, a.getId());
                ps1.setString(2, a.getNombre());
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(sqlSeg)) {
                ps2.setInt(1, a.getId());
                ps2.setInt(2, a.getSeguridad().getPin());
                ps2.setString(3, a.getSeguridad().getNivel());
                ps2.executeUpdate();
            }

            conn.commit();
            cache.add(a); // Actualizamos la lista local
            logger.info("Guardado exitoso de Astronauta y Seguridad.");

        } catch (SQLException e) {
            if (conn != null) { try { conn.rollback(); } catch (SQLException ex) {} }
            throw new AppException("Fallo en transacción 1:1: " + e.getMessage());
        } finally {
            conector.release();
        }
    }

    /**
     * MÉTODO FALTANTE: Busca en la lista (caché) que cargamos al principio
     */
    public Astronauta buscarPorId(int id) {
        for (Astronauta a : cache) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public List<Astronauta> getAstronautas() {
        return cache;
    }
}
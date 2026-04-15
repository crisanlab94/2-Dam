package servicioCon2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.Seguridad;
import repositorioCon2.RepositorioAstronauta;
import repositorioCon2.RepositorioSeguridad;
import util.MySqlConector;

public class ServicioSeguridad {
    private static final Logger logger = LogManager.getLogger(ServicioSeguridad.class);
    
    private final RepositorioAstronauta repoAstro;
    private final RepositorioSeguridad repoSeg;
    private final MySqlConector conector;

    public ServicioSeguridad(MySqlConector conector) {
        this.conector = conector;
        // Instanciamos los repositorios pasándoles el conector
        this.repoAstro = new RepositorioAstronauta(conector);
        this.repoSeg = new RepositorioSeguridad(conector);
    }

    /**
     * ALTA TRANSACCIONAL: Inserta en ambas tablas o en ninguna.
     * Garantiza que el astronauta siempre nazca con su seguridad asociada.
     */
    public void registrarAstronauta(Astronauta a) throws AppException {
        // Validación de datos obligatorios
        if (a.getNombre() == null || a.getNombre().trim().isEmpty()) {
            throw new AppException("Error: No se puede registrar un astronauta sin nombre.");
        }
        
        if (a.getSeguridad() == null) {
            throw new AppException("Error: Todo astronauta requiere un perfil de seguridad inicial.");
        }

        Connection conn = null;
        try {
            conn = conector.getConnect();
            conn.setAutoCommit(false); // Bloqueamos el guardado automático para crear la transacción

            // Ejecutamos las operaciones en orden
            repoAstro.guardar(a, conn);
            repoSeg.guardar(a.getId(), a.getSeguridad(), conn);

            // Si ambos repositorios terminan sin lanzar SQLException, confirmamos
            conn.commit();
            logger.info("Transacción exitosa: Astronauta {} registrado con seguridad nivel {}", 
                        a.getNombre(), a.getSeguridad().getNivel());

        } catch (SQLException e) {
            // Si algo falla, revertimos cualquier cambio parcial en la base de datos
            if (conn != null) {
                try {
                    conn.rollback();
                    logger.warn("Se ha realizado un ROLLBACK debido a un error en el registro.");
                } catch (SQLException ex) {
                    logger.error("Error crítico durante el rollback: " + ex.getMessage());
                }
            }
            throw new AppException("Error en la persistencia de datos 1:1: " + e.getMessage());
        } finally {
            conector.release();
        }
    }

    /**
     * REPORTE INTEGRAL: Cruza los datos de los dos repositorios para 
     * devolver la información completa de cada astronauta.
     */
    public void mostrarExpedienteCompleto() throws AppException {
        Connection conn = null;
        try {
            conn = conector.getConnect();
            
            // 1. Obtenemos la lista de astronautas (datos básicos)
            List<Astronauta> lista = repoAstro.readBasico();
            
            logger.info("========== LISTADO DE PERSONAL Y SEGURIDAD ==========");
            
            for (Astronauta a : lista) {
                // 2. Por cada astronauta, recuperamos su seguridad específica
                Seguridad s = repoSeg.buscarPorAstronauta(a.getId(), conn);
                
                // 3. Vinculamos los datos para que el objeto esté completo
                a.setSeguridad(s);
                
                // Imprimimos el objeto completo usando su toString()
                logger.info(a.toString());
            }
            
            logger.info("=====================================================");
            
        } catch (SQLException e) {
            throw new AppException("Error al recuperar los expedientes: " + e.getMessage());
        } finally {
            conector.release();
        }
    }
}
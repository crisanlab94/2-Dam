package servicio;

import java.sql.Date;
import java.util.List;

import modelo.AppException;
import modelo.Entrenamiento;
import modelo.Socio;
import modelo.TipoCuota;
import repositorio.RepositorioEntrenamiento;
import repositorio.RepositorioSocio;

public class ServicioGimnasio {
    private final RepositorioEntrenamiento repoEnt;
    private final RepositorioSocio repoSoc;

    public ServicioGimnasio(RepositorioEntrenamiento repoEnt, RepositorioSocio repoSoc) {
        this.repoEnt = repoEnt;
        this.repoSoc = repoSoc;
    }

    /**
     * APARTADO 1: Obtener socios (Hidratación)
     */
    public List<Socio> obtenerSocios() throws AppException {
        try {
            List<Socio> socios = repoSoc.getSocio();
            List<Entrenamiento> entrenamientos = repoEnt.getEntrenamientos();

            for (Socio s : socios) {
                s.getEntrenamientos().clear();
            }

            for (Entrenamiento e : entrenamientos) {
                Socio buscaSocio = repoSoc.buscarPorId(e.getSocio().getId());
                if (buscaSocio != null) {
                    buscaSocio.getEntrenamientos().add(e);
                }
            }
            return socios;
        } catch (Exception e) {
            throw new AppException("Error al cargar los datos: " + e.getMessage());
        }
    }
    
    public void registrarSocio(Socio s) throws AppException {
        try {
            
            repoSoc.guardar(s);
        } catch (AppException e) {
            throw new AppException("No se pudo registrar al socio: " + e.getMessage());
        }
    }
    
    public List<Socio> getUsuariosPorPlan(TipoCuota cuota) throws AppException {
        try {
            return repoSoc.getSociosPorCuota(cuota);
        } catch (AppException e) {
            throw new AppException("Error al filtrar socios .");
        }
    }

    public int contarSociosActivos() throws AppException {
        try {
            return repoSoc.contarSociosActivos();
        } catch (AppException e) {
            throw new AppException("Error al contar socios Activos.");
        }
    }
    
    public List<Entrenamiento> obtenerTopFuerza() throws AppException {
        try {
            return repoEnt.obtenerTop3Fuerza();
        } catch (AppException e) {
            throw new AppException("Error al cargar el ranking de entrenamientos de fuerza.");
        }
    }
    
    public int eliminarEntrenamientosAntiguos(Date fecha) throws AppException {
        try {
            return repoEnt.eliminarEntrenamientosAntiguos(fecha);
        } catch (AppException e) {
            throw new AppException("Error al eliminar entrenamientos: " + e.getMessage());
        }
    }
}

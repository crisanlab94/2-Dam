package servicio;

import java.util.List;

import modelo.AppException;
import modelo.Reproduccion;
import modelo.TipoPlan;
import modelo.Usuario;
import repositorio.RepositorioReproduccion;
import repositorio.RepositorioUsuario;

public class ServicioUsuarios {
    private final RepositorioReproduccion repoRepro;
    private final RepositorioUsuario repoUsu;

    public ServicioUsuarios(RepositorioReproduccion repoRepro, RepositorioUsuario repoUsu) {
        this.repoRepro = repoRepro;
        this.repoUsu = repoUsu;
    }

    /**
     * APARTADO 1: Obtener Usuarios (Hidratación) [cite: 1]
     */
    public List<Usuario> obtenerTodo() throws AppException {
        try {
            List<Usuario> usuarios = repoUsu.getUsuario();
            List<Reproduccion> reproducciones = repoRepro.getReproduccion();

            for (Usuario u : usuarios) {
                u.getHistorial().clear();
            }

            for (Reproduccion r : reproducciones) {
                Usuario buscaUsuario = repoUsu.buscarPorId(r.getUsuario().getId());
                if (buscaUsuario != null) {
                    buscaUsuario.getHistorial().add(r);
                }
            }
            return usuarios;
        } catch (Exception e) {
            throw new AppException("Error al cargar los datos: " + e.getMessage());
        }
    }

    /**
     * APARTADO 4: Registrar Usuario con validación [cite: 4]
     */
    public void registrarUsuario(Usuario u) throws AppException {
        try {
            // El RepositorioUsuario.guardar ya tiene la validación de buscarPorId
            repoUsu.guardar(u);
        } catch (AppException e) {
            throw new AppException("No se pudo registrar al usuario: " + e.getMessage());
        }
    }

    /**
     * APARTADO 3: Filtrar por plan [cite: 3]
     */
    public List<Usuario> getUsuariosPorPlan(TipoPlan plan) throws AppException {
        try {
            return repoUsu.getUsuariosPorPlan(plan);
        } catch (AppException e) {
            throw new AppException("Error al filtrar usuarios.");
        }
    }

    /**
     * APARTADO 5: Contar VIP [cite: 5]
     */
    public int contarVIP() throws AppException {
        try {
            return repoUsu.contarUsuariosVIP();
        } catch (AppException e) {
            throw new AppException("Error al contar usuarios VIP.");
        }
    }

    /**
     * APARTADO 7: Eliminar por dispositivo [cite: 7]
     */
    public int borrarPorDispositivo(String dispo) throws AppException {
        try {
            return repoUsu.eliminarPorDispositivo(dispo);
        } catch (AppException e) {
            throw new AppException("Error al eliminar dispositivos: " + e.getMessage());
        }
    }
}
package controlador;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.AppException;
import modelo.Reproduccion;
import modelo.TipoPlan;
import modelo.Usuario;
import repositorio.RepositorioReproduccion;
import repositorio.RepositorioUsuario;
import servicio.ServicioUsuarios;
import util.MySqlConector;

public class ControladorStreaming {
	private static final Logger logger = LogManager.getLogger(ControladorStreaming.class);

    public static void main(String[] args) {
        try {
            // 0. INICIALIZACIÓN DE CAPAS (Apartado 0)
            MySqlConector conector = new MySqlConector();
            RepositorioUsuario repoUsu = new RepositorioUsuario(conector);
            RepositorioReproduccion repoRepro = new RepositorioReproduccion(conector);
            ServicioUsuarios servicio = new ServicioUsuarios(repoRepro, repoUsu);

            logger.info("=== SISTEMA NETPLIS INICIADO ===");

            // APARTADO 4: Alta con Validación (Prueba de error)
            logger.info("--- 4. PRUEBA DE ALTA (VALIDACIÓN ID DUPLICADO) ---");
            try {
                // Intentamos registrar un ID que ya existe en el SQL (usr01)
                Usuario duplicado = new Usuario("usr01", "Ana Duplicada", "ana2@mail.com", TipoPlan.VIP, 0, true, "pc");
                servicio.registrarUsuario(duplicado);
            } catch (AppException e) {
                logger.warn("Excepción controlada correctamente: " + e.getMessage());
            }

            // APARTADO 2: Búsqueda por ID (Uso de .equals y sin break)
            logger.info("--- 2. BÚSQUEDA DE USUARIO POR ID (usr02) ---");
            Usuario buscado = repoUsu.buscarPorId("usr02");
            if (buscado != null) {
                System.out.println("Encontrado: " + buscado.getNombre() + " con plan " + buscado.getPlanActivo());
            }

            // APARTADO 3: Filtro por Plan y Ordenación por Email
            logger.info("--- 3. USUARIOS CON PLAN 'MENSUAL' (ORDEN EMAIL ASC) ---");
            List<Usuario> mensuales = repoUsu.getUsuariosPorPlan(TipoPlan.MENSUAL);
            for (Usuario u : mensuales) {
                System.out.println(" > " + u.getEmail() + " - " + u.getNombre());
            }

            // APARTADO 5: Estadística (COUNT en SQL)
            logger.info("--- 5. ESTADÍSTICA DE USUARIOS VIP ---");
            int totalVip = servicio.contarVIP();
            System.out.println("Total de suscriptores VIP: " + totalVip);

            // APARTADO 6: Consulta Compleja (Top 3 específicos)
            logger.info("--- 6. TOP 3: MENSUAL, SIN NOTIFICACIONES, ANDROID14 ---");
            List<Usuario> top3 = repoUsu.obtenerTop3Mensuales();
            for (Usuario u : top3) {
                System.out.println(" [TOP] " + u.getNombre() + " | Dispositivo: " + u.getDispositivoPrincipal());
            }

            // APARTADO 7: Borrado Parametrizado
            logger.info("--- 7. ELIMINANDO USUARIOS CON DISPOSITIVO 'ios17' ---");
            int eliminados = servicio.borrarPorDispositivo("ios17");
            logger.info("Se han eliminado " + eliminados + " usuarios del sistema.");

            // APARTADO 1: Listado Completo con HIDRATACIÓN
            logger.info("--- 1. LISTADO FINAL HIDRATADO (USUARIOS + REPRODUCCIONES) ---");
            List<Usuario> listaFinal = servicio.obtenerTodo();
            for (Usuario u : listaFinal) {
                System.out.println("\n" + u.toString());
                // Mostramos su historial de reproducciones (la lista interna hidratada)
                if (u.getHistorial().isEmpty()) {
                    System.out.println("   (Sin reproducciones recientes)");
                } else {
                    for (Reproduccion r : u.getHistorial()) {
                        System.out.println("   -> " + r.toString());
                    }
                }
            }

        } catch (AppException e) {
            // Manejo de errores de negocio (Apartado 0)
            logger.error("ERROR DE NEGOCIO: " + e.getMessage());
        } catch (Exception e) {
            // Errores críticos inesperados
            logger.error("ERROR CRÍTICO DEL SISTEMA: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

package controlador;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.PlanActivo;
import modelo.Preferencias;
import modelo.Usuario;
import repositorio.RepositorioSinAutoincrementar;
import servicio.ServicioUsuarios;
import util.MySqlConector;

public class GestionaUsuario {
    private static final Logger logger = LogManager.getLogger(GestionaUsuario.class);

    public static void main(String[] args) {
        try {
            // ============================================================
            // APARTADO 0: INICIALIZACIÓN DE ARQUITECTURA
            // ============================================================
            MySqlConector conector = new MySqlConector();
            RepositorioSinAutoincrementar repo = new RepositorioSinAutoincrementar(conector);
            ServicioUsuarios servicio = new ServicioUsuarios(repo);

            logger.info("=== INICIANDO TODAS LAS PRUEBAS (RÚBRICA + EXTRAS) ===");

            // ============================================================
            // SECCIÓN 1: LISTADOS DETALLADOS (TODA LA INFO + PREFERENCIAS)
            // ============================================================
            logger.info("--- [A1] LISTADO INICIAL COMPLETO ---");
            List<Usuario> todos = servicio.obtenerTodosLosUsuarios();
            for (Usuario u : todos) {
                System.out.println(u); // Usa el toString detallado que creamos
            }

            // ============================================================
            // SECCIÓN 2: PRUEBAS ESPECÍFICAS DE LA RÚBRICA
            // ============================================================
            logger.info("--- [RÚBRICA: APARTADOS 2 AL 6] ---");

            // A2: Buscar por ID específico (usr002)
            Usuario uId = servicio.buscarPorId("usr002");
            logger.info("A2. Encontrado usr002: " + uId.getUsername() + " | Idioma: " + uId.getPreferencias().getIdioma());

            // A3: Filtrar por Plan ANUAL y ordenar por Email
            logger.info("A3. Plan ANUAL ordenado por Email:");
            for (Usuario u : servicio.listarPorPlanOrdenado(PlanActivo.ANUAL)) {
                logger.info("    > " + u.getEmail());
            }

            // A4: Registrar nuevo usuario (Falla si ya existe)
            Preferencias pNuevas = new Preferencias(true, "ES", true, false);
            Usuario nuevo = new Usuario("usr099", "CrisExamen", "cris@fitdroid.com", PlanActivo.VIP, "android14", pNuevas);
            servicio.registrarUsuario(nuevo);
            logger.info("A4. Registro exitoso de usr099.");

            // A5: Estadísticas (Número de VIPs)
            servicio.mostrarEstadisticasVip();

            // A6: Filtro complejo (Top 3 android14 + MENSUAL/Push=false)
            logger.info("A6. Top 3 Filtro Complejo:");
            for (Usuario u : servicio.obtenerTop3Android14()) {
                logger.info("    > " + u.getUsername() + " | Plan: " + u.getPlan_activo());
            }

            // ============================================================
            // SECCIÓN 3: TUS BÚSQUEDAS PERSONALIZADAS (EXTRAS)
            // ============================================================
            logger.info("--- [MIS BÚSQUEDAS EXTRAS] ---");

            logger.info("Extra 1. Contienen 'User': " + servicio.buscarPorNombre("User").size());
            
            logger.info("Extra 2. Empiezan por 'A': " + servicio.buscarPorNombreQueEmpiezaPorA().size());

            Usuario uEx = servicio.buscarPorNombreYEmail("User001", "user001@fitdroid.com");
            logger.info("Extra 3. Nombre y Email exacto: " + (uEx != null ? uEx.getUsername() : "No encontrado"));

            logger.info("Extra 4. Nombre 'User' + Plan MENSUAL: " + servicio.buscarPorNombreYPlan("User", PlanActivo.MENSUAL).size());

            logger.info("Extra 5. Nombre 'User' + Idioma 'ES': " + servicio.buscarPorNombreEIdioma("User", "ES").size());

            // Esta búsqueda ahora funcionará porque aún no hemos ejecutado el borrado (A7)
            Usuario uLim = servicio.buscarPorLimiteDatosYEmail(true, "user003@fitdroid.com");
            logger.info("Extra 6. Límite Datos + Email (usr003): " + (uLim != null ? "ENCONTRADO" : "N/A"));

            // ============================================================
            // SECCIÓN 4: ACTUALIZACIONES Y LISTADOS VIP DETALLADOS
            // ============================================================
            logger.info("--- [NUEVAS FUNCIONALIDADES: ACTUALIZACIÓN Y VIPS] ---");

            // Listar VIPs con toda la info (No solo el número)
            logger.info("Listado detallado de todos los VIPs:");
            for (Usuario u : servicio.obtenerVipsConInfo()) {
                System.out.println(u);
            }

            // Actualización masiva
            logger.info("Ejecutando actualización: MENSUAL -> TRIMESTRAL...");
            servicio.masivoMensualATrimestral();
            
            logger.info("Comprobando cambio en usr001 (antes era MENSUAL):");
            System.out.println(servicio.buscarPorId("usr001"));

            // ============================================================
            // SECCIÓN 5: APARTADO 7 (BORRADO FINAL)
            // ============================================================
            logger.info("--- [RÚBRICA: APARTADO 7] ---");
            logger.info("Borrando todos los dispositivos 'android12' de la BD y Caché...");
            servicio.borrarDispositivos("android12");

            logger.info("=== TODAS LAS PRUEBAS FINALIZADAS CON ÉXITO ===");

        } catch (AppException e) {
            // Manejo de errores según Apartado 0
            logger.error("HA OCURRIDO UN ERROR: " + e.getMessage());
        }
    }
}
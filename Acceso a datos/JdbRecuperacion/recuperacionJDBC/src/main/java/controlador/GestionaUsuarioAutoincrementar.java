package controlador;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException; 
import modelo.PlanActivo; 
import modelo.Preferencias;
import modelo.Usuario;
import repositorio.RepositorioConAutoincrementar;
import servicio.ServicioUsuarios;
import servicio.ServicioUsuariosAutoincremental;
import util.MySqlConector; 

/**
 * Clase GestionaUsuario: Orquesta el flujo de la aplicación cuando el ID lo genera la BD.
 */
public class GestionaUsuarioAutoincrementar {
    private static final Logger logger = LogManager.getLogger(GestionaUsuarioAutoincrementar.class);

    public static void main(String[] args) {
        try {
            // 1. INICIALIZACIÓN DE CAPAS
            MySqlConector conector = new MySqlConector(); // [cite: 5]
            RepositorioConAutoincrementar repo = new RepositorioConAutoincrementar(conector);
            ServicioUsuariosAutoincremental servicio = new ServicioUsuariosAutoincremental(repo);

            logger.info("--- INICIANDO CONTROLADOR (VERSION AUTOINCREMENTAL) ---");

            // 2. PRUEBA: REGISTRAR NUEVO USUARIO
            // Creamos preferencias
            Preferencias misPref = new Preferencias(false, "FR", true, true); // [cite: 3]
            
            // Creamos el usuario SIN ID (el constructor recibirá 0 o null para el id)
            // En el objeto Usuario.java, el atributo 'id' ahora debe ser 'int' [cite: 4]
            Usuario nuevo = new Usuario();
            nuevo.setUsername("NuevosHorizontes");
            nuevo.setEmail("horizonte@fitdroid.com");
            nuevo.setPlan_activo(PlanActivo.TRIMESTRAL); // [cite: 2]
            nuevo.setDispositivo("iphone16");
            nuevo.setPreferencias(misPref);

            logger.info("Enviando usuario al servicio para asignación de ID automática...");
            servicio.registrarUsuario(nuevo); 
            
            // El ID ahora ya no es 0, es el que ha dado MySQL
            logger.info("¡Usuario registrado! ID asignado por la base de datos: " + nuevo.getId());

            // 3. PRUEBA: BUSCAR POR ID NUMÉRICO (int)
            // Supongamos que queremos buscar el usuario que acabamos de crear
            int idABuscar = nuevo.getId();
            Usuario encontrado = servicio.buscarPorId(idABuscar);
            logger.info("Búsqueda exitosa: " + encontrado.getUsername() + " (Email: " + encontrado.getEmail() + ")");

            // 4. PRUEBA: LISTAR TODOS DESDE LA CACHÉ
            List<Usuario> listaCaché = servicio.obtenerTodosLosUsuarios();
            logger.info("Usuarios totales en el sistema (Caché): " + listaCaché.size());

            // 5. PRUEBA: FILTRADO POR EMAIL
            // Como el ID es automático, el email es nuestro gran aliado para búsquedas únicas
            Usuario porEmail = repo.buscarPorEmail("user040@fitdroid.com");
            if (porEmail != null) {
                logger.info("Usuario localizado por email: " + porEmail.getUsername());
            }

            logger.info("--- CIERRE DE OPERACIONES ---");

        } catch (AppException e) {
            // MANEJO DE EXCEPCIONES: Captura errores de negocio (Email repetido) y técnicos (SQL) [cite: 1]
            logger.error("ERROR DETECTADO: " + e.getMessage());
        }
    }
}
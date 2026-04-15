package servicio;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Astronauta;
import modelo.EstadoMision;
import modelo.Mision;
import repositorio.RepositorioAstronauta;
import repositorio.RepositorioMision;

public class ServicioGalactico {
    private static final Logger logger = LogManager.getLogger(ServicioGalactico.class);
    
    private final RepositorioAstronauta repoAstronauta;
    private final RepositorioMision repoMision;

    public ServicioGalactico(RepositorioAstronauta repoAstronauta, RepositorioMision repoMision) {
        this.repoAstronauta = repoAstronauta;
        this.repoMision = repoMision;
    }

    /**
     * 1. ALTA DE ASTRONAUTA: Validación lógica en caché antes de guardar.
     */
    public void darDeAltaAstronauta(Astronauta a) throws AppException {
        logger.info("Servicio: Validando registro de astronauta: " + a.getNombre());

        if (repoAstronauta.buscarPorId(a.getId()) != null) {
            logger.warn("El astronauta con ID " + a.getId() + " ya está registrado.");
            throw new AppException("Error: El ID " + a.getId() + " ya pertenece a otro astronauta.");
        }

        repoAstronauta.guardar(a);
        logger.info("Astronauta dado de alta correctamente.");
    }
    
    /**
     * 2. PROCESAR RESULTADO: Calcula puntos y ordena actualización al repo.
     */
    public void registrarResultadoMision(Mision m) throws AppException {
        Astronauta astro = m.getAstronauta();
        int horasExtra = 0;

        if (m.getEstado() == EstadoMision.EXITO) {
            horasExtra = 100;
        } else if (m.getEstado() == EstadoMision.FALLO) {
            horasExtra = 10;
        }

        int totalHoras = astro.getHorasVuelo() + horasExtra;
        
        // Actualizamos el objeto en memoria
        astro.setHorasVuelo(totalHoras);
        m.setUltimaConexion(LocalDateTime.now());

        // LLAMADA AL REPOSITORIO: Él se encarga del SQL
        repoAstronauta.actualizarHoras(astro.getId(), totalHoras);
        
        logger.info("Misión procesada. " + astro.getNombre() + " suma " + horasExtra + " horas.");
    }
    
    /**
     * 3. ESTADÍSTICAS: Lógica de ordenación y búsqueda.
     */
    public List<Mision> mostrarInformeMisiones() {
        List<Mision> lista = repoMision.getMisiones();
        
        // Ordenación Burbuja por fecha (Ascendente)
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                if (lista.get(j).getFechaLanzamiento().isAfter(lista.get(j+1).getFechaLanzamiento())) {
                    Mision temp = lista.get(j);
                    lista.set(j, lista.get(j+1));
                    lista.set(j+1, temp);
                }
            }
        }
        return lista;
    }
    
    public Astronauta obtenerLiderGalactico() {
        List<Astronauta> lista = repoAstronauta.getAstronautas();
        if (lista.isEmpty()) return null;

        Astronauta lider = lista.get(0);
        for (Astronauta a : lista) {
            if (a.getHorasVuelo() > lider.getHorasVuelo()) {
                lider = a;
            }
        }
        return lider;
    }
    
    public void generarRankingPilotos() {
        List<Astronauta> ranking = repoAstronauta.getAstronautas();

        // Ordenación Burbuja por horas (Descendente)
        for (int i = 0; i < ranking.size() - 1; i++) {
            for (int j = 0; j < ranking.size() - i - 1; j++) {
                if (ranking.get(j).getHorasVuelo() < ranking.get(j+1).getHorasVuelo()) {
                    Astronauta temp = ranking.get(j);
                    ranking.set(j, ranking.get(j+1));
                    ranking.set(j+1, temp);
                }
            }
        }

        System.out.println("--- RANKING DE PILOTOS GALÁCTICOS ---");
        for (int i = 0; i < ranking.size(); i++) {
            System.out.println((i+1) + "º " + ranking.get(i).getNombre() + ": " + ranking.get(i).getHorasVuelo() + "h");
        }
    }
    
    public void mostrarDetalleAgencia() {
        List<Astronauta> todosLosAstros = repoAstronauta.getAstronautas();
        List<Mision> todasLasMisiones = repoMision.getMisiones();

        logger.info("========== EXPEDIENTE COMPLETO DE LA AGENCIA ==========");

        for (Astronauta a : todosLosAstros) {
            logger.info("👨‍🚀 ASTRONAUTA: {} ({}) | Horas: {}h | Activo: {}", 
                        a.getNombre(), a.getRango(), a.getHorasVuelo(), a.isActivo());
            
            boolean tieneMisiones = false;
            
            for (Mision m : todasLasMisiones) {
                if (m.getAstronauta().getId() == a.getId()) {
                    logger.info("   -> Misión: [{}] | Fecha: {} | Estado: {}", 
                                m.getNombreNave(), m.getFechaLanzamiento(), m.getEstado());
                    tieneMisiones = true;
                }
            }

            if (!tieneMisiones) {
                logger.info("   -> (Sin misiones registradas)");
            }
        }
        logger.info("=======================================================");
    }
}
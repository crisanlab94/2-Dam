package servicioExamen;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modeloExamen.Equipo;
import modeloExamen.Piloto;
import repositorioExamen.RepositorioEquipos;



public class ServicioEquipos {
    private static final Logger logger = LogManager.getLogger(ServicioEquipos.class);
    private RepositorioEquipos repoEquipos;

    public ServicioEquipos(RepositorioEquipos repoEquipos) {
        this.repoEquipos = repoEquipos;
    }

    // PASO 1: Carga inicial de equipos
    public void cargarEquipos(List<Equipo> listaEquiposXML) {
        logger.info("Iniciando carga de equipos en el sistema...");
        repoEquipos.agregarListaEquipos(listaEquiposXML);
    }

    // PASO 3: Vincular la lista de pilotos cargada en el Paso 2
    public void vincularPilotosAEquipos(List<Piloto> listaPilotosXML) {
        logger.info("Vinculando lista de pilotos a sus equipos correspondientes...");
        for (Piloto p : listaPilotosXML) {
            // El servicio saca el ID del piloto y le pide al REPOSITORIO que haga el CRUD
            String idEquipo = p.getIdentificadorEquipo();
            repoEquipos.añadirPilotoAEquipo(idEquipo, p);
        }
    }
    
    public List<Piloto> obtenerPilotosDeUnEquipo(String idEquipo) {
        return repoEquipos.recuperarPilotosDeEquipo(idEquipo);
    }

    public RepositorioEquipos getRepoEquipos() {
        return repoEquipos;
    }
    
    public List<Piloto> filtrarPilotosPorPuntos(int puntos) {
        return repoEquipos.obtenerPilotosConPuntosMayores(puntos);
    }
}
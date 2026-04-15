package servicio;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.AppException;
import modelo.Sala;
import modelo.Sede;
import repositorio.RepositorioSala;
import repositorio.RepositorioSede;
import util.MySqlConector;

public class ServicioSede {
    private static final Logger logger = LogManager.getLogger(ServicioSede.class);
    private RepositorioSede repoSede;
    private RepositorioSala repoSala;

    public ServicioSede() throws AppException {
        MySqlConector con = new MySqlConector();
        this.repoSede = new RepositorioSede(con);
        this.repoSala = new RepositorioSala(con);
    }

    public void darAltaSede(Sede s) throws AppException {
        if (repoSede.buscarPorId(s.getId()) != null) throw new AppException("ID Sede ya existe.");
        repoSede.guardar(s);
        logger.info("Servicio: Sede {} registrada.", s.getId());
    }

    public void darAltaSala(Sala s, int idSede) throws AppException {
        if (repoSede.buscarPorId(idSede) == null) throw new AppException("Sede no existe.");
        if (repoSala.buscarPorId(s.getId()) != null) throw new AppException("ID Sala ya existe.");
        repoSala.guardar(s, idSede);
    }

    public List<Sede> obtenerTodoHidratado() throws AppException {
        List<Sede> sedes = repoSede.getLista();
        for (Sede s : sedes) {
            s.setListaSalas(repoSala.buscarPorSede(s.getId()));
        }
        return sedes;
    }

    public List<Sala> obtenerTop3Capacidad() throws AppException {
        List<Sala> todas = repoSala.read();
        Collections.sort(todas, new Comparator<Sala>() {
            @Override
            public int compare(Sala s1, Sala s2) {
                if (s1.getCapacidad() < s2.getCapacidad()) return 1;
                if (s1.getCapacidad() > s2.getCapacidad()) return -1;
                return 0;
            }
        });
        return todas.subList(0, Math.min(todas.size(), 3));
    }

    public void eliminarSedeCompleta(int id) throws AppException {
        repoSala.borrarPorSede(id);
        repoSede.borrar(id);
    }
}

package servicio;

import java.util.List;

import modelo.Equipo;
import modelo.Fase;
import repositorio.RepositorioEquipo;
import repositorio.RepositorioFase;
import repositorio.RepositorioJugador;

public class ServicioTorneo {
	private  RepositorioEquipo repositorioEquipo;
    private RepositorioFase repositorioFase;
    private RepositorioJugador repositorioJugador;
    
	public ServicioTorneo() {
		super();
		this.repositorioEquipo = new RepositorioEquipo();
        this.repositorioFase = new RepositorioFase();
        this.repositorioJugador = new RepositorioJugador();
	}
    
	public void addEquipo(Equipo e) {
        repositorioEquipo.create(e);
    }

   
    public void addFase(Fase f) {
        repositorioFase.create(f);
    }

    
    public void actualizarEquipo(Equipo e) {
        repositorioEquipo.update(e);
    }
	
    public List<Equipo> obtenerFinalistas() {
        return repositorioEquipo.buscarDosMejores();
    }
    

}

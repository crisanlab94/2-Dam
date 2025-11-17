package servicio;


import modelo.Enfrentamiento;
import repositorio.RepositorioEnfrentamiento;
import repositorio.RepositorioEquipo;
import util.TorneoException;

public class TorneoService {
	private RepositorioEnfrentamiento enfrentamiento;
	private RepositorioEquipo equipos;
	
	
	public TorneoService(RepositorioEnfrentamiento enfrentamiento, RepositorioEquipo equipos) {
		super();
		this.enfrentamiento = enfrentamiento;
		this.equipos = equipos;
	}
	
	  public void agregarEnfrentamiento(Enfrentamiento e) throws TorneoException {
	        enfrentamiento.agregarEnfrentamiento(e);
	    }

}

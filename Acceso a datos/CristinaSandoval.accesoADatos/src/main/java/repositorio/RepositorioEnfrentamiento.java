package repositorio;

import java.util.ArrayList;
import java.util.List;

import modelo.Enfrentamiento;
import util.TorneoException;

public class RepositorioEnfrentamiento {
	List<Enfrentamiento> enfrentamientos;

	public RepositorioEnfrentamiento(List<Enfrentamiento> enfrentamientos) {
		super();
		this.enfrentamientos = new ArrayList<Enfrentamiento>();
	}
	
	 public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) throws TorneoException {
	    	if(!this.enfrentamientos.contains(enfrentamiento)) {
	    		enfrentamientos.add(enfrentamiento);
	    	} else {
	    		throw new TorneoException ("El centro ya está añadido");
	    	}
	       
	    }
	 
	  public List<Enfrentamiento> getListaEnfrentamiento() {
	        return enfrentamientos;
	    }
	    
	

}

package repositorio;

import modelo.Persona;
import util.AbstractDao;

public class RepositorioPersona extends AbstractDao <Persona> {
	public RepositorioPersona() {
		setClase(Persona.class);
	} 

}

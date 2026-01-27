package repositorio;


import modelo.Sala;
import util.AbstractDao;

public class RepositorioSala extends AbstractDao <Sala> {
	public RepositorioSala() {
		setClase(Sala.class);
	}

}

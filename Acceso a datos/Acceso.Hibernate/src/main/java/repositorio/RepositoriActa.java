package repositorio;

import modelo.Acta;
import util.AbstractDao;

public class RepositoriActa extends AbstractDao <Acta> {
	
	public RepositoriActa() {
		setClase(Acta.class);
	}


}

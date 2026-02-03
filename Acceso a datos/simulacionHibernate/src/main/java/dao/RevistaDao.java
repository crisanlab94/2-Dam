package dao;

import modelos.Revista;
import utiles.AbstractDao;

public class RevistaDao extends AbstractDao<Revista> {

	public RevistaDao() {
		setClase(Revista.class);
	}
}

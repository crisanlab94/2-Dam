package dao;

import modelos.Autor;
import utiles.AbstractDao;

public class AutorDao extends AbstractDao<Autor> {

	public AutorDao() {
		setClase(Autor.class);
	}

}
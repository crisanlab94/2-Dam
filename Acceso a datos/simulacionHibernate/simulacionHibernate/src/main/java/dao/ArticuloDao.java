package dao;

import modelos.Articulo;
import utiles.AbstractDao;

public class ArticuloDao extends AbstractDao<Articulo> {

	public ArticuloDao() {
		setClase(Articulo.class);
	}

}
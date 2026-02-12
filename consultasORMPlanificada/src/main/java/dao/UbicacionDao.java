package dao;

import modelos.Ubicacion;
import utiles.AbstractDao;

public class UbicacionDao extends AbstractDao<Ubicacion> {

	public UbicacionDao() {
		setClase(Ubicacion.class);
	}
}

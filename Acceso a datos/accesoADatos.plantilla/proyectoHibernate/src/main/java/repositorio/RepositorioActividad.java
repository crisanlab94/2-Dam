package repositorio;


import java.util.List;

import org.hibernate.Session;

import modelo.Actividad;
import util.AbstractDao;
import util.HibernateUtil;


public class RepositorioActividad extends AbstractDao<Actividad> {
	
	public RepositorioActividad() {
		setClase(Actividad.class);
	}

	//Buscar actividad por nombre

	public Actividad getActividadPorNombre(String nombreAct) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        Actividad a = sesion.createQuery("FROM Actividad a WHERE a.nombre = :nom", Actividad.class)
                            .setParameter("nom", nombreAct)
                            .uniqueResult();
        sesion.close();
        return a;
    }
	
	//Actividades que tengan más de x socios 
	public List<Actividad> getActividadesPopulares(int minimoSocios) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        // Usamos size() para contar elementos en la relación N:M
        String hql = "FROM Actividad a WHERE size(a.socios) >= :min";
        List<Actividad> lista = sesion.createQuery(hql, Actividad.class)
                                      .setParameter("min", minimoSocios)
                                      .list();
        sesion.close();
        return lista;
    }
	
	//Buscar actividad por id
	public Actividad getActividadPorIdHql(int id) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    Actividad a = sesion.createQuery("FROM Actividad a WHERE a.id = :id", Actividad.class)
	                        .setParameter("id", id)
	                        .uniqueResult();
	    sesion.close();
	    return a;
	}
}
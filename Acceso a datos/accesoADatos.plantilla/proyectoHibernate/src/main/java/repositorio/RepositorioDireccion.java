package repositorio;


import java.util.List;

import org.hibernate.Session;

import modelo.Direccion;
import util.AbstractDao;
import util.HibernateUtil;


public class RepositorioDireccion extends AbstractDao<Direccion> {
    
    public RepositorioDireccion() {
        setClase(Direccion.class);
    }

    //Busca por ciudad y ordena por calle
    public List<Direccion> getDireccionesPorCiudadOrdenadas(String ciudad) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Direccion d WHERE d.ciudad = :ciu ORDER BY d.calle ASC";
        List<Direccion> lista = sesion.createQuery(hql, Direccion.class)
                                      .setParameter("ciu", ciudad)
                                      .list();
        sesion.close();
        return lista;
    }
    //Busqueda por x letras en la calle
    public List<Direccion> buscarPorCalle(String textoCalle) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Direccion d WHERE d.calle LIKE :texto";
        List<Direccion> lista = sesion.createQuery(hql, Direccion.class)
                                      .setParameter("texto", "%" + textoCalle + "%")
                                      .list();
        sesion.close();
        return lista;
    }
}
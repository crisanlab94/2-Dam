package repositorio;

import java.util.List;

import org.hibernate.Session;

import modelo.FichaMedica;
import util.AbstractDao;
import util.HibernateUtil;


public class RepositorioFichaMedica extends AbstractDao<FichaMedica> {
    
    public RepositorioFichaMedica() {
        setClase(FichaMedica.class);
    }


    //Mayor peso registrado 
    public Double getPesoMaximo() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        
        Double max = sesion.createQuery("SELECT MAX(f.peso) FROM FichaMedica f", Double.class)
                           .uniqueResult();
        sesion.close();
        return max;
    }
    
    //Personas que esten en un rango de peso
    public List<FichaMedica> getFichasPorRangoPeso(double min, double max) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM FichaMedica f WHERE f.peso BETWEEN :min AND :max";
        List<FichaMedica> lista = sesion.createQuery(hql, FichaMedica.class)
                                        .setParameter("min", min)
                                        .setParameter("max", max)
                                        .list();
        sesion.close();
        return lista;
    }
}
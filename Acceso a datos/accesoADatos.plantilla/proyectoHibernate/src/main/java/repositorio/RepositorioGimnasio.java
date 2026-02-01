package repositorio;


import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import modelo.Gimnasio;
import util.AbstractDao;
import util.HibernateUtil;
import org.hibernate.Transaction; 
import jakarta.persistence.criteria.CriteriaBuilder;


public class RepositorioGimnasio extends AbstractDao<Gimnasio> {
	
	public RepositorioGimnasio() {
		setClase(Gimnasio.class);
	}

	//Restringir a 1 
	public Gimnasio getPrimerGimnasio() {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    Gimnasio g = sesion.createQuery("FROM Gimnasio", Gimnasio.class)
	                       .setMaxResults(1) //Usamos .setMaxResults(1) para decirle a SQL que solo queremos la primera fila.
	                       .uniqueResult();
	    sesion.close();
	    return g;
	}
	
	//Primer gym con 3 entrenadores
	public Gimnasio getPrimerGimnasioConTresEntrenadores() {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    
	    // HQL: "Busca un Gimnasio g donde el tamaño de su lista de entrenadores sea 3"
	    String hql = "FROM Gimnasio g WHERE size(g.entrenadores) = 3";
	    
	    Gimnasio g = sesion.createQuery(hql, Gimnasio.class)
	                       .setMaxResults(1)  //Si quiero mas de uno pongo .setMaxResults(2) y el numero que sea
	                       .uniqueResult(); // si es más de uno tengo que poner .list() en lugar de .uniqueResult()
	                       
	    sesion.close();
	    return g;
	}
	
	
	//Parametrizada + filtro: gimnasios de una ciudad concreta
	public List<Gimnasio> getGimnasiosPorCiudad(String ciudad) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    List<Gimnasio> lista = sesion.createQuery("FROM Gimnasio g WHERE g.direccion.ciudad = :ciu", Gimnasio.class)
	                                 .setParameter("ciu", ciudad)
	                                 .list();
	    sesion.close();
	    return lista;
	}
	
	// Actualización con CriteriaBuilder 
	public void actualizarNombreCriteria(int id, String nuevoNombre) {
	    Session sesion = HibernateUtil.getFactoriaSession().openSession();
	    Transaction tx = null;
    
    try {
        tx = sesion.beginTransaction();
        
        // Preparamos el constructor de consultas (CriteriaBuilder)
        CriteriaBuilder cb = sesion.getCriteriaBuilder();
        
        // Definimos que vamos a hacer un UPDATE sobre la clase Gimnasio
        CriteriaUpdate<Gimnasio> update = cb.createCriteriaUpdate(Gimnasio.class);
        Root<Gimnasio> root = update.from(Gimnasio.class);
        
        // SET: ¿Qué campo cambiamos? El atributo "nombre" por el nuevo valor
        update.set("nombre", nuevoNombre);
        
        // WHERE: ¿A quién? Al que tenga el ID que pasamos por parámetro
        update.where(cb.equal(root.get("id"), id));
        
        // jecución: Se lanza la mutación a la base de datos
        sesion.createMutationQuery(update).executeUpdate();
        
        tx.commit(); // Guardado definitivo
    } catch (Exception e) {
        if (tx != null) tx.rollback(); // Si falla, volvemos atrás
        e.printStackTrace();
    } finally {
        sesion.close();
    }

}
}
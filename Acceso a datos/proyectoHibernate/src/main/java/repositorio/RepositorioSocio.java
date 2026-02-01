package repositorio;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controlador.GestionaGimnasio;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import modelo.Socio;
import modelo.TipoSuscripcion;
import util.AbstractDao;
import util.HibernateUtil;


public class RepositorioSocio extends AbstractDao<Socio> {
	  private static final Logger logger = LogManager.getLogger(RepositorioSocio.class);

	public RepositorioSocio() {
		setClase(Socio.class);
	}

	//Devuelve solo el nombre de los socios 
	public List<String> getAllNombresSocios() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        List<String> nombres = sesion.createQuery("SELECT s.nombre FROM Socio s", String.class).list();
        sesion.close();
        return nombres;
    }
	
	
	//Filtar + ordenar 3 últimos socios
		public List<Socio> getTresUltimosSocios() {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    // Ordenamos por ID de forma descendente para que los últimos creados salgan arriba
		    List<Socio> lista = sesion.createQuery("FROM Socio s ORDER BY s.id DESC", Socio.class)
		                              .setMaxResults(3) 
		                              .list(); 
		    sesion.close();
		    return lista;
		}
		
		//Peso medio de los socios
		
		public Double getPesoMedioSocios() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // HQL navega por los atributos de las clases
	        Double media = sesion.createQuery("SELECT AVG(s.fichaMedica.peso) FROM Socio s", Double.class).uniqueResult();
	        sesion.close();
	        return media;
	    }
		
		// Buscar socios de un gimnasio concreto (Filtro por relación)
		public List<Socio> getSociosPorGimnasio(int idGym) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        
	        String hql = "FROM Socio s WHERE s.gimnasio.id = :id";
	        List<Socio> lista = sesion.createQuery(hql, Socio.class)
	                                  .setParameter("id", idGym)
	                                  .list();
	        sesion.close();
	        return lista;
	    }
	    
	 // Buscar por nombre parcial (Uso de LIKE)
	    // Si buscas "Ma", te devolverá "Mario", "Maria",
	    public List<Socio> buscarSociosPorNombre(String trozoNombre) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String hql = "FROM Socio s WHERE s.nombre LIKE :nombre";
	        List<Socio> lista = sesion.createQuery(hql, Socio.class)
	                                  .setParameter("nombre", "%" + trozoNombre + "%")
	                                  .list();
	        sesion.close();
	        return lista;
	    }
	    
	 // Buscar socio por su Ficha Médica (Relación 1:1)
	  
	    public Socio getSocioPorFichaMedica(int idFicha) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String hql = "FROM Socio s WHERE s.fichaMedica.id = :idFicha";
	        Socio s = sesion.createQuery(hql, Socio.class)
	                        .setParameter("idFicha", idFicha)
	                        .uniqueResult();
	        sesion.close();
	        return s;
	    }
	    
	    //Buscar socio que empieze por x letra
	    public List<Socio> buscarSociosPorComienzoLetra(String trozoNombre) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        String hql = "FROM Socio s WHERE s.nombre LIKE :nombre";
	        
	        List<Socio> lista = sesion.createQuery(hql, Socio.class)
	                                 
	                                  .setParameter("nombre", trozoNombre + "%") 
	                                  .list();
	        sesion.close();
	        return lista;
	    }
	    
	    //Socio por tipo de suscripcion
	    public List<Socio> getSociosPorTipoSuscripcion(TipoSuscripcion tipo) {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // Hibernate traduce el objeto Enum automáticamente a String para la consulta
	        String hql = "FROM Socio s WHERE s.suscripcion = :tipo";
	        List<Socio> lista = sesion.createQuery(hql, Socio.class)
	                                  .setParameter("tipo", tipo)
	                                  .list();
	        sesion.close();
	        return lista;
	    }
	    
	    
	    //Consulta: Estado de pago
	    public List<Socio> getSociosPendientesDePago() {
	        Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        // En HQL puedes usar 'false' o 'true' directamente
	        String hql = "FROM Socio s WHERE s.estaAlCorrientePago = false";
	        List<Socio> lista = sesion.createQuery(hql, Socio.class).list();
	        sesion.close();
	        return lista;
	    }
		
	//Borrar socio  por nombre
		public void borrarSocioPorNombreCriteria(String nombreSocio) {
			Session sesion = HibernateUtil.getFactoriaSession().openSession();
	        Transaction tx = null;
	        try {
	            tx = sesion.beginTransaction();
	            CriteriaBuilder cb = sesion.getCriteriaBuilder();
	            
	            // Preparamos el borrado de la clase Socio
	            CriteriaDelete<Socio> delete = cb.createCriteriaDelete(Socio.class);
	            Root<Socio> root = delete.from(Socio.class);

	            // ¿A quién borramos? 
	            // Usamos la variable 'nombreSocio' que nos llega del controlador
	            delete.where(cb.equal(root.get("nombre"), nombreSocio));

	            // Se ejecuta la orden
	            sesion.createMutationQuery(delete).executeUpdate();
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        } finally {
	            sesion.close();
	        }
	    }
		
		//Añadir socio a actividad
		public void inscribirSocioEnActividad(int idSocio, int idActividad) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    Transaction tx = null;
		    
		    try {
		        tx = sesion.beginTransaction();

		        // Obtenemos los dos objetos de la base de datos
		        Socio socio = sesion.get(Socio.class, idSocio);
		     
		        modelo.Actividad actividad = sesion.get(modelo.Actividad.class, idActividad);

		        if (socio != null && actividad != null) {
		            // Añadimos la actividad a la colección del socio
		           
		            socio.getActividades().add(actividad);
		            
		            // Al hacer merge/update, Hibernate inserta automáticamente 
		            // una fila en la tabla intermedia 
		            sesion.merge(socio);
		        }

		        tx.commit();
		    } catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		    } finally {
		        sesion.close();
		    }
		}
		
		public void borrarSocioDeActividad(int idSocio, int idActividad) {
		    Session sesion = HibernateUtil.getFactoriaSession().openSession();
		    Transaction tx = null;
		    
		    try {
		        tx = sesion.beginTransaction();

		       
		        Socio socio = sesion.get(Socio.class, idSocio);
		        modelo.Actividad actividad = sesion.get(modelo.Actividad.class, idActividad);

		        if (socio != null && actividad != null) {
		            
		            socio.getActividades().remove(actividad);
		            
		            
		            sesion.merge(socio);
		        }

		        tx.commit();
		        logger.info("Socio borrado con éxito.");
		     
		        
		    } catch (Exception e) {
		        if (tx != null) tx.rollback();
		        e.printStackTrace();
		    } finally {
		        sesion.close();
		    }
		}
	}
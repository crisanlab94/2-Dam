package repositorio;


import java.util.List;

import org.hibernate.Session;

import modelo.Entrenador;
import modelo.Turno;
import util.AbstractDao;
import util.HibernateUtil;


public class RepositorioEntrenador extends AbstractDao<Entrenador> {
    
    public RepositorioEntrenador() {
        setClase(Entrenador.class);
    }

    //Consulta que devuelve 2 campos: nombre y especialidad
    
    public List<Entrenador> getInfoBasicaEntrenadores() {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		// Seleccionamos solo dos atributos específicos
		String hql = "SELECT e.nombre, e.especialidad FROM Entrenador e";
		List<Entrenador> resultados = sesion.createQuery(hql, Entrenador.class).list();
		sesion.close();
		return resultados;
	}
    
    //Filtro por turno de entrenador
    public List<Entrenador> getEntrenadoresPorTurno(Turno turno) {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenador e WHERE e.turno = :turno";
        List<Entrenador> lista = sesion.createQuery(hql, Entrenador.class)
                                      .setParameter("turno", turno)
                                      .list();
        sesion.close();
        return lista;
    }
    
    //Filtro: El entrenador esta activo(trabaja actualmente o esta de baja)
    public List<Entrenador> getEntrenadoresEnPlantillaActiva() {
        Session sesion = HibernateUtil.getFactoriaSession().openSession();
        String hql = "FROM Entrenador e WHERE e.estaActivo = true";
        List<Entrenador> lista = sesion.createQuery(hql, Entrenador.class).list();
        sesion.close();
        return lista;
    }
    
    //Consulta parametrizada: Entrenadores en total(Count) + filtrar y ordenar
    //Entrenadores de una especialidad  ordenador por nombre alfabeticamente
    
    public List<Entrenador> getEntrenadoresPorEspecialidad(String espec) {
		Session sesion = HibernateUtil.getFactoriaSession().openSession();
		// :espec es el "hueco" (parámetro) que rellenaremos en controlador
		String hql = "FROM Entrenador e WHERE e.especialidad = :espec ORDER BY e.nombre ASC";
		
		List<Entrenador> lista = sesion.createQuery(hql, Entrenador.class)
				.setParameter("espec", espec) // Rellenamos el hueco con el valor del paréntesis
				.list();
		
		sesion.close();
		return lista;
	}
}

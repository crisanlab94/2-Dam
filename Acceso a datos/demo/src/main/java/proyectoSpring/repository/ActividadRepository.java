package proyectoSpring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoSpring.models.Actividad;


public interface ActividadRepository extends JpaRepository<Actividad,Long> {
	  List<Actividad> findAll();
	    Set<Actividad> findByCategory(String nombre);
	    Actividad findProductById(long id);

}



package proyectoSpring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoSpring.models.Actividad;
import proyectoSpring.models.Dificultad;


public interface ActividadRepository extends JpaRepository<Actividad,Long> {
	  List<Actividad> findAll();
	    Set<Actividad> findByNombre(String nombre);
	    List<Actividad> findByDificultad(Dificultad dificultad);
	    Actividad findActividadById(long id);

}



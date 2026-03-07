package proyectoSpringSecurity.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoSpringSecurity.models.Gimnasio;


public interface GimnasioRepository extends JpaRepository<Gimnasio,Long> {
	  List<Gimnasio> findAll();
	    Set<Gimnasio> findByNombre(String nombre);
	    Gimnasio findGimnasioById(long id);

}

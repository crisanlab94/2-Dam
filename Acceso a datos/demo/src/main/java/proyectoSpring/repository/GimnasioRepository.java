package proyectoSpring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;


import proyectoSpring.models.Gimnasio;


public interface GimnasioRepository extends JpaRepository<Gimnasio,Long> {
	  List<Gimnasio> findAll();
	    Set<Gimnasio> findByCategory(String nombre);
	    Gimnasio findProductById(long id);

}

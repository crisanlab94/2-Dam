package proyectoSpring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectoSpring.models.Socio;

public interface SocioRepository extends JpaRepository<Socio,Long> {
	  List<Socio> findAll();
	    Set<Socio> findByCategory(String nombre);
	    Socio findProductById(long id);


}

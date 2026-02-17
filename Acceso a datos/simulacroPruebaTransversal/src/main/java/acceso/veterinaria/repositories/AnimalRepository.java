package acceso.veterinaria.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.TipoAnimal;



public interface AnimalRepository extends JpaRepository<Animal,Long> {
	  List<Animal> findAll();
	    Set<Animal> findByNombre(String nombre);
	    Set<Animal> findByTipo(TipoAnimal tipo);
	    Animal findByIdAnimal(long idAnimal);
	  

}

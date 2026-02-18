package acceso.veterinaria.services;

import java.util.List;
import java.util.Set;



import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.TipoAnimal;
import acceso.veterinaria.models.Vacuna;





public interface AnimalesService {
	
    List<Animal> findAllAnimal();
    Set<Animal> findByNombreAnimal(String nombre);
    Set<Animal> findByTipoAnimal(TipoAnimal tipo);
    Animal findAnimalByIdAnimal(long idAnimal);
    public Animal createAnimal(Animal animal) ;
    
    
    List<Vacuna> findAllVacuna();
	  Set<Vacuna> findByPartidaVacuna(String partida);
	  Vacuna findByNombreVacuna(String nombre);
	  Vacuna findVacunaById(long idVacuna);
	  Vacuna createVacunaById(Vacuna vacuna);
	  public Vacuna createVacuna(Vacuna vacuna) ;
	  Vacuna vincularVacunaAAnimal(Long idAnimal, Vacuna vacunaRequest);
  
	  public Animal updateAnimal(Long id, Animal datosNuevos);
	  public void deleteAnimal(Long id);
	  public Animal updateAnimalJSON(Long id, Animal datosNuevos);
	  public void deleteAnimalJSON(Long id);
	  public void desvincularVacunaDeAnimal(Long idAnimal, Long idVacuna);
	 // relacion 1:N public Vacuna vincularVacunaAAnimal1N(Long idAnimal, Vacuna vacunaNueva);
    

}

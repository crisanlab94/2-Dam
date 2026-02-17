package acceso.veterinaria.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.TipoAnimal;
import acceso.veterinaria.models.Vacuna;
import acceso.veterinaria.repositories.AnimalRepository;
import acceso.veterinaria.repositories.VacunaRepository;
import exception.AnimalNotFoundException;
import exception.VacunaNotFoundException;



@Service
public class AnimalesServiceImpl implements AnimalesService{
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private VacunaRepository vacunaRepository;


	@Override
	public List<Animal> findAllAnimal() {
	
		return animalRepository.findAll();
	}

	@Override
	public Set<Animal> findByNombreAnimal(String nombre) {
	
		return animalRepository.findByNombre(nombre);
	}

	@Override
	public Set<Animal> findByTipoAnimal(TipoAnimal tipo) {
		// TODO Auto-generated method stub
		return animalRepository.findByTipo(tipo);
	}

	@Override
	public Animal findAnimalByIdAnimal(long idAnimal) {
		 Optional<Animal> OpcionalAnimal = animalRepository.findById(idAnimal);
	        return OpcionalAnimal.orElseThrow(() -> new AnimalNotFoundException(idAnimal));
	}

	@Override
	public Animal createAnimal(Animal animal) {
	    if (animal.getVacunas() != null && !animal.getVacunas().isEmpty()) {
	        List<Vacuna> vacunasReales = new ArrayList<>();
	        for (Vacuna v : animal.getVacunas()) {
	            Vacuna vacunaReal = vacunaRepository.findById(v.getIdVacuna())
	                .orElseThrow(() -> new VacunaNotFoundException(v.getIdVacuna()));
	            
	            // IMPORTANTE: Como Vacuna es la dueña, añadimos el animal a la vacuna
	            vacunaReal.getAnimales().add(animal); 
	            
	            vacunasReales.add(vacunaReal);
	        }
	        animal.setVacunas(vacunasReales);
	    }
	    return animalRepository.save(animal);
	}

	@Override
	public List<Vacuna> findAllVacuna() {
		return vacunaRepository.findAll();
	}

	@Override
	public Set<Vacuna> findByPartidaVacuna(String partida) {
		return vacunaRepository.findByPartida(partida);
	}

	@Override
	public Set<Vacuna> findByNombreVacuna(String nombre) {
		return vacunaRepository.findByNombre(nombre);
	}

	@Override
	public Vacuna findVacunaById(long idVacuna) {
		 Optional<Vacuna> OpcionalVacuna = vacunaRepository.findById(idVacuna);
	        return OpcionalVacuna.orElseThrow(() -> new VacunaNotFoundException(idVacuna));
	}

	@Override
	public Vacuna createVacuna(Vacuna vacuna) {
	    // Verificamos si la vacuna trae animales para vincular
	    if (vacuna.getAnimales() != null && !vacuna.getAnimales().isEmpty()) {
	        List<Animal> animalesReales = new ArrayList<>();

	        for (Animal a : vacuna.getAnimales()) {
	            // Buscamos cada animal por su ID en el repositorio de animales
	            Animal animalReal = animalRepository.findById(a.getIdAnimal())
	                .orElseThrow(() -> new RuntimeException("Animal no encontrado con ID: " + a.getIdAnimal()));
	            
	            animalesReales.add(animalReal);
	        }
	        
	        // Asignamos la lista de animales reales a la vacuna
	        vacuna.setAnimales(animalesReales);
	    }

	    // Guardamos la vacuna 
	    return vacunaRepository.save(vacuna);
	}
	
	
	
	
}

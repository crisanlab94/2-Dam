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
import jakarta.transaction.Transactional;



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
	    // 1. Aseguramos que la lista de vacunas esté inicializada pero vacía 
	    // (según pide el enunciado para un animal nuevo)
	    if (animal.getVacunas() == null) {
	        animal.setVacunas(new ArrayList<>());
	    }

	    // 2. Guardamos al animal (lado débil)
	    // Esto crea la fila en la tabla 'animales', pero no toca la tabla intermedia
	    Animal animalGuardado = animalRepository.save(animal);

	    // 3. Un solo return
	    return animalGuardado;
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
	public Vacuna findByNombreVacuna(String nombre) {
	    return vacunaRepository.findByNombre(nombre); // Ahora sí coinciden (Vacuna con Vacuna)
	}

	@Override
	public Vacuna findVacunaById(long idVacuna) {
		 Optional<Vacuna> OpcionalVacuna = vacunaRepository.findById(idVacuna);
	        return OpcionalVacuna.orElseThrow(() -> new VacunaNotFoundException(idVacuna));
	}
	@Override
	public Vacuna createVacuna(Vacuna vacuna) {
	    // 1. Intentamos buscar si ya existe por nombre
	    Vacuna resultado = vacunaRepository.findByNombre(vacuna.getNombre());

	    // 2. Si no existe (es null), la guardamos para que deje de serlo
	    if (resultado == null) {
	        resultado = vacunaRepository.save(vacuna);
	    }

	    // 3. Un único punto de salida: devolvemos la encontrada o la recién creada
	    return resultado;
	}

	@Override
	public Vacuna createVacunaById(Vacuna vacuna) {
	    // Solo guarda. Si el objeto ya trae un ID, hará Update. Si no, hará Insert.
	    return vacunaRepository.save(vacuna);
	}
	
	
	@Override
	@Transactional // IMPORTANTE: Asegura que toda la operación sea atómica (o se hace todo o nada)
	public Vacuna vincularVacunaAAnimal(Long idAnimal, Vacuna vacunaRequest) {
	    
	    // 1. BUSCAR EL ANIMAL (El que ya existe)
	    // Usamos el id que viene en la URL. Si no existe, lanzamos excepción.
	    Animal animal = animalRepository.findById(idAnimal)
	            .orElseThrow(() -> new RuntimeException("Error: El animal con ID " + idAnimal + " no existe."));

	    // 2. BUSCAR LA VACUNA (Lógica: "Si no existe, se crea")
	    // Buscamos en la BBDD por un campo único, como el nombre
	    Vacuna vacunaDefinitiva = vacunaRepository.findByNombre(vacunaRequest.getNombre());

	    if (vacunaDefinitiva == null) {
	        // CASO A: La vacuna NO existe en la BBDD
	        vacunaDefinitiva = vacunaRequest; // Usamos la que viene del JSON
	        
	        // Inicializamos la lista de animales por seguridad (evita NullPointerException)
	        if (vacunaDefinitiva.getAnimales() == null) {
	            vacunaDefinitiva.setAnimales(new ArrayList<>());
	        }
	    } else {
	        // CASO B: La vacuna SÍ existe
	        // Actualizamos sus datos con lo que venga en el JSON (partida, farmacéutica...)
	        vacunaDefinitiva.setPartida(vacunaRequest.getPartida());
	        vacunaDefinitiva.setFarmaceutica(vacunaRequest.getFarmaceutica());
	    }

	    // 3. VINCULACIÓN TÉCNICA (Lado Fuerte manda)
	    // Comprobamos si el animal ya tenía esa vacuna para no duplicar en la tabla intermedia
	    if (!vacunaDefinitiva.getAnimales().contains(animal)) {
	        // Metemos al débil (Animal) dentro de la lista del fuerte (Vacuna)
	        vacunaDefinitiva.getAnimales().add(animal);
	    }

	    // 4. GUARDAR EL DUEÑO DE LA RELACIÓN
	    // Al guardar 'vacunaDefinitiva', JPA hace dos cosas:
	    // - Si es nueva, hace un INSERT en 'vacunas'. Si existía, un UPDATE.
	    // - Hace un INSERT en la tabla intermedia 'animal_vacuna'.
	    return vacunaRepository.save(vacunaDefinitiva);
	}
}
	


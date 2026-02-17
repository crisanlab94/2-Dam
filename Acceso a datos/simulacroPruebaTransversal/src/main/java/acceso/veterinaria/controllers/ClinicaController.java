package acceso.veterinaria.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.Vacuna;
import acceso.veterinaria.services.AnimalesService;
import exception.VacunaNotFoundException;




@Controller
@RequestMapping("/clinica")
public class ClinicaController {
	@Autowired
	private AnimalesService animalesService;
	
	@GetMapping("/") 
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/listaAnimales")
	public String catalogoAnimales(Model model) {
		model.addAttribute("animales", animalesService.findAllAnimal());
		return "animales";
	}

	@GetMapping("/listaVacunas")
	public String catalogoVacunas(Model model) {
		model.addAttribute("vacunas", animalesService.findAllVacuna());
		return "listaVacunas";
	}

	
	//Lado debil tiene el atributo mappedbBy
	//metes al debil en el fuerte
	//al guerdar el fuerte, hibernate mira la lista de animales y escribe en la tabla animal_vacuna
	//el lado fuerte quien tiene permiso para escribir en la tabal intermedia
	/**
	 * 1. Agregar Animal
	 * Retorna el ID o un mensaje de error.
	 */
	@PostMapping("/animal")
	@ResponseBody
	public Map<String, Object> addAnimal(@RequestBody Animal animal) {
	    Map<String, Object> respuesta = new HashMap<>();
	    Object resultado; // Usamos Object para que acepte tanto el Long (ID) como el String (Error)

	    try {
	        // 1. Intentamos crear el animal en el servicio
	        Animal animalGuardado = animalesService.createAnimal(animal);

	        // 2. Comprobamos si se ha guardado correctamente
	        if (animalGuardado != null && animalGuardado.getIdAnimal() != null) {
	            // Si quieres que el ID sea estrictamente una cadena: String.valueOf(...)
	            resultado = animalGuardado.getIdAnimal(); 
	        } else {
	            resultado = "No se ha podido agregar el animal";
	        }
	    } catch (Exception e) {
	        // Si el service lanza cualquier error (ej. base de datos caída)
	        resultado = "No se ha podido agregar el animal";
	    }

	    // 3. Único punto de retorno
	    respuesta.put("resultado", resultado);
	    return respuesta;
	}
	
	/**
	 * 2. Agregar Vacuna (Sueltas)
	 * Retorna el ID de la vacuna o un mensaje de error.
	 */
	@PostMapping("/vacuna")
	@ResponseBody
	public Map<String, Object> addVacuna(@RequestBody Vacuna vacuna) {
		Map<String, Object> respuesta = new HashMap<>();
		Object resultado;

		try {
			// Usamos el service que ya comprueba si existe por nombre
			Vacuna vGuardada = animalesService.createVacuna(vacuna);
			
			if (vGuardada != null && vGuardada.getIdVacuna() != null) {
				resultado = vGuardada.getIdVacuna();
			} else {
				resultado = "No se ha podido agregar la vacuna";
			}
		} catch (Exception e) {
			resultado = "No se ha podido agregar la vacuna";
		}

		respuesta.put("resultado", resultado);
		return respuesta;
	}
	
	
	
	
	//Lado fuerte 
	//tiene anotación @JoinTable
	//Para que la tabla intermedia se guarde hay que save/create el objeto que tiene @JoinTable
	@PostMapping("/agregaVacuna/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> agregaVacuna(@PathVariable("id") long id, @RequestBody Vacuna vacuna) {
	    Map<String, Object> respuesta = new HashMap<>();
	    HttpStatus estado; // Variable para guardar el estado del resultado

	    try {
	        // Intentamos la operación
	        Vacuna vNueva = animalesService.vincularVacunaAAnimal(id, vacuna);
	        
	        respuesta.put("idVacuna", vNueva.getIdVacuna());
	        respuesta.put("idAnimal", id);
	        estado = HttpStatus.OK; // Todo ha ido bien
	        
	    } catch (RuntimeException e) {
	        // Si hay error (ej. animal no encontrado), capturamos el mensaje
	        respuesta.put("error", e.getMessage());
	        estado = HttpStatus.NOT_FOUND; // Error de recurso no encontrado
	    }

	    
	    return new ResponseEntity<>(respuesta, estado);
	}
	
	@GetMapping("/animal/{id}")
	public String getAnimalById(@PathVariable("id") Long idAnimal, Model model) {
		Animal animalEncontrado = animalesService.findAnimalByIdAnimal(idAnimal);
		//primero nombre que usas en html
		model.addAttribute("animal", animalEncontrado);
		return "detalleAnimal";
	}
	
	// Método de consulta que usa la excepción
    @GetMapping("/vacuna/{id}")
    @ResponseBody
    public ResponseEntity<Vacuna> getVacuna(@PathVariable Long id) {
        Vacuna v = animalesService.findVacunaById(id);
        return ResponseEntity.ok(v);
    }
    
 // --- CAPTURADOR DE TU EXCEPCIÓN ---
    @ExceptionHandler(VacunaNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleVacunaNotFound(VacunaNotFoundException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("estado", 404);
        error.put("mensaje", e.getMessage());
        error.put("codigo_error", "VAC-101"); 
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	//Si fuera 1:N Animal --> Vacuna
	// En 1:N el fuerte (Vacuna) recibe al padre (Animal)
	//vacuna.setAnimal(animal); 
	//animalesService.createVacuna(vacuna);
}

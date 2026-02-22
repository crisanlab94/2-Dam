package acceso.veterinaria.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	/**
	 * 1. Agregar Animal (Versión exacta según el enunciado)
	 * Retorna el ID (Long) directamente o el String de error.
	 */
	@PostMapping("/animal")
	@ResponseBody
	public Object addAnimal(@RequestBody Animal animal) {
	    try {
	        // 1. Intentamos crear el animal
	        Animal animalGuardado = animalesService.createAnimal(animal);

	        // 2. Comprobamos éxito
	        if (animalGuardado != null && animalGuardado.getIdAnimal() != null) {
	            // RETORNO DIRECTO: Devuelve el número (id)
	            return animalGuardado.getIdAnimal(); 
	        } else {
	            // RETORNO DIRECTO: Devuelve el texto exacto
	            return "No se ha podido agregar el animal";
	        }
	    } catch (Exception e) {
	        // Si hay cualquier fallo en el servicio o DB
	        return "No se ha podido agregar el animal";
	    }
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
	//JSON
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
    
    
    /**
     * 3. Actualizar Animal respuesta en cadena
     * Se usa PUT para modificaciones completas.
     */
    @PutMapping("/animal/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateAnimal(@PathVariable("id") Long id, @RequestBody Animal animalDatosNuevos) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            // Llamamos al servicio para actualizar 
            Animal animalActualizado = animalesService.updateAnimal(id, animalDatosNuevos);
            
            respuesta.put("resultado", "Animal actualizado correctamente");
            respuesta.put("id", animalActualizado.getIdAnimal());
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
            
        } catch (Exception e) {
            respuesta.put("resultado", "No se ha podido actualizar el animal");
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 4. Eliminar Animal respuesta en cadena 
     * Borra el recurso identificado por el ID.
     */
    @DeleteMapping("/animal/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteAnimal(@PathVariable("id") Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            // Llamamos al servicio para eliminar
            animalesService.deleteAnimal(id);
            
            respuesta.put("resultado", "Animal eliminado con éxito");
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
            
        } catch (Exception e) {
            respuesta.put("resultado", "Error al intentar eliminar el animal");
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * 3. Actualizar Animal
     * Envía un JSON con los nuevos datos al ID indicado.
     */
    @PutMapping("/animal/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateAnimalJSON(@PathVariable("id") Long id, @RequestBody Animal animalDatosNuevos) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            Animal animalActualizado = animalesService.updateAnimalJSON(id, animalDatosNuevos);
            
            // Construimos el JSON de éxito
            respuesta.put("resultado", "Animal actualizado correctamente");
            respuesta.put("id_actualizado", animalActualizado.getIdAnimal());
            respuesta.put("nuevo_nombre", animalActualizado.getNombre());
            
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
            
        } catch (Exception e) {
            // JSON de error
            respuesta.put("resultado", "No se ha podido actualizar el animal");
            respuesta.put("mensaje_error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 4. Eliminar Animal
     * Borra el registro y devuelve confirmación en JSON.
     */
    @DeleteMapping("/animal/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteAnimalJSON(@PathVariable("id") Long id) {
        Map<String, Object> respuesta = new HashMap<>();
        try {
            animalesService.deleteAnimalJSON(id);
            
            // Construimos el JSON de éxito
            respuesta.put("resultado", "Animal eliminado con éxito");
            respuesta.put("id_eliminado", id);
            
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
            
        } catch (Exception e) {
            // JSON de error
            respuesta.put("resultado", "Error al intentar eliminar el animal");
            respuesta.put("mensaje_error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * 5. Desvincular Vacuna de un Animal
     * Borra la relación en la tabla intermedia, pero no borra la vacuna del sistema.
     */
    @DeleteMapping("/animal/{idAnimal}/vacuna/{idVacuna}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> desvincularVacuna(
            @PathVariable("idAnimal") Long idAnimal, 
            @PathVariable("idVacuna") Long idVacuna) {
        
        Map<String, Object> respuesta = new HashMap<>();
        try {
            animalesService.desvincularVacunaDeAnimal(idAnimal, idVacuna);
            
            // Respuesta JSON de éxito
            respuesta.put("resultado", "Vacuna quitada del animal con éxito");
            respuesta.put("idAnimal", idAnimal);
            respuesta.put("idVacunaDesvinculada", idVacuna);
            
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
            
        } catch (Exception e) {
            // Respuesta JSON de error
            respuesta.put("resultado", "No se pudo desvincular la vacuna");
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
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
	
    /**
	//Si fuera 1:N Animal --> Vacuna
	// En 1:N el fuerte (Vacuna) recibe al padre (Animal)
    
     * Agregar Vacuna a un Animal (Relación 1:N)
     * En 1:N, le asignamos el "padre" (Animal) al "hijo" (Vacuna).
     
    @PostMapping("/animal/{id}/vacuna")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> agregaVacuna1N(@PathVariable("id") long id, @RequestBody Vacuna vacuna) {
        Map<String, Object> respuesta = new HashMap<>();
        
        try {
            // El servicio ahora asocia el animal a la vacuna y guarda la vacuna
            Vacuna vGuardada = animalesService.vincularVacunaAAnimal1N(id, vacuna);
            
            respuesta.put("resultado", "Vacuna asociada al animal correctamente");
            respuesta.put("idVacuna", vGuardada.getIdVacuna());
            respuesta.put("idAnimalAsociado", id);
            
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
            
        } catch (RuntimeException e) {
            respuesta.put("error", "No se pudo asociar: " + e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
        }
       
    }
    */
}

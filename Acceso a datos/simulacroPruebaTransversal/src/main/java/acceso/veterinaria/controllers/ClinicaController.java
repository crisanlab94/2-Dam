package acceso.veterinaria.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import acceso.veterinaria.models.Animal;
import acceso.veterinaria.models.Vacuna;
import acceso.veterinaria.services.AnimalesService;




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
	    Animal animalGuardado = animalesService.createAnimal(animal);

	    if (animalGuardado != null && animalGuardado.getIdAnimal() != null) {
	        respuesta.put("resultado", animalGuardado.getIdAnimal());
	    } else {
	        respuesta.put("resultado", "No se ha podido agregar el animal");
	    }

	    return respuesta;
	}

	//Lado fuerte 
	@PostMapping("/agregaVacuna/{id}")
	@ResponseBody
	public Map<String, Object> agregaVacuna(@PathVariable("id") long id, @RequestBody Vacuna vacuna) {
	    Map<String, Object> respuesta = new HashMap<>();
	    
	    // Buscamos el animal por su ID
	    Animal animal = animalesService.findAnimalByIdAnimal(id);

	    // Vinculamos: añadimos el animal a la lista de la vacuna
	    vacuna.getAnimales().add(animal);

	    // Creamos/Guardamos la vacuna con la relación
	    Vacuna vNueva = animalesService.createVacuna(vacuna);

	    // Metemos los IDs en el mapa como pide el ejercicio
	    respuesta.put("idVacuna", vNueva.getIdVacuna());
	    respuesta.put("idAnimal", animal.getIdAnimal());

	    return respuesta;
	}
	
	@GetMapping("/animal/{id}")
	public String getAnimaltById(@PathVariable("id") Long idAnimal, Model model) {
		Animal animalEncontrado = animalesService.findAnimalByIdAnimal(idAnimal);
		//primero nombre que usas en html
		model.addAttribute("animal", animalEncontrado);
		return "detalleAnimal";
	}
	
	//Si fuera 1:N Animal --> Vacuna
	// En 1:N el fuerte (Vacuna) recibe al padre (Animal)
	//vacuna.setAnimal(animal); 
	//animalesService.createVacuna(vacuna);
}

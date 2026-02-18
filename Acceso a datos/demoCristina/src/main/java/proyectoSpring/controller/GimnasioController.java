package proyectoSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import exception.ActividadNotFoundException;
import exception.GimnasioNotFoundException;
import exception.SocioNotFoundException;
import proyectoSpring.models.*;
import proyectoSpring.service.*;


@Controller
@RequestMapping("/miweb")
public class GimnasioController {

	@Autowired
	private ActividadService actividadService;
	@Autowired
	private GimnasioService gimnasioService;
	@Autowired
	private SocioService socioService;

	// --- BLOQUE DE RESPUESTAS HTML (Thymeleaf) ---
	

	@GetMapping("/") 
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/listaSocios")
	public String catalogoSocio(Model model) {
		model.addAttribute("socios", socioService.findAll());
		return "listaSocios";
	}

	@GetMapping("/listaGimnasios")
	public String catalogoGimnasio(Model model) {
		model.addAttribute("gimnasios", gimnasioService.findAll());
		return "listaGimnasios";
	}

	@GetMapping("/listaActividades")
	public String catalogoActividad(Model model) {
		model.addAttribute("actividades", actividadService.findAll());
		return "listaActividades";
	}

	@GetMapping("/socios/morosos")
	public String verMorosos(Model model) {
		model.addAttribute("socios", socioService.findMorosos());
		return "listaSociosMorosos";
	}

	

	@GetMapping("/socio/{id}")
	public String getSociotById(@PathVariable Long id, Model model) {
		model.addAttribute("detalleSocio", socioService.findSocioById(id));
		return "detalleSocio";
	}

	@GetMapping("/gimnasio/{id}")
	public String getGimnasiotById(@PathVariable Long id, Model model) {
		model.addAttribute("detalleGimnasio", gimnasioService.findGimnasioById(id));
		return "detalleGimnasio";
	}

	@GetMapping("/actividad/{id}")
	public String getActividadById(@PathVariable Long id, Model model) {
		model.addAttribute("detalleActividad", actividadService.findActividadById(id));
		return "detalleActividad";
	}

	// --- BLOQUE DE RESPUESTAS JSON  ---
	

	@PostMapping("/socio")
	@ResponseBody
	public ResponseEntity<Socio> addSocio(@RequestBody Socio socio) {
		Socio addedSocio = socioService.createSocio(socio);
		return new ResponseEntity<>(addedSocio, HttpStatus.CREATED);
	}

	@PostMapping("/gimnasio")
	@ResponseBody
	public ResponseEntity<Gimnasio> addGimnasio(@RequestBody Gimnasio gimnasio) {
		Gimnasio addedGimnasio = gimnasioService.createGimnasio(gimnasio);
		return new ResponseEntity<>(addedGimnasio, HttpStatus.CREATED);
	}

	@PostMapping("/actividad")
	@ResponseBody
	public ResponseEntity<Actividad> addActividad(@RequestBody Actividad actividad) {
		Actividad addedActividad = actividadService.createActividad(actividad);
		return new ResponseEntity<>(addedActividad, HttpStatus.CREATED);
	}

	@PutMapping("/socio/actualizar/{id}")
	@ResponseBody
	public ResponseEntity<Socio> actualizar(@PathVariable long id, @RequestBody Socio socio) {
		Socio actualizado = socioService.updateSocio(id, socio);
		return new ResponseEntity<>(actualizado, HttpStatus.OK);
	}

	// --- BLOQUE DE RESPUESTAS TIPO CADENA (String) ---
	

	@PutMapping("/socio/pagar/{id}")
	@ResponseBody
	public ResponseEntity<String> marcarPagoSocio(@PathVariable Long id) {
		socioService.actualizarEstadoPago(id, true);
		return ResponseEntity.ok("Atributo modificado: El socio con ID " + id + " ahora está al corriente de pago.");
	}

	@DeleteMapping("/socio/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<String> eliminar(@PathVariable Long id) {
		socioService.deleteById(id);
		return ResponseEntity.ok("Socio con ID " + id + " eliminado correctamente.");
	}

	// --- MANEJO DE EXCEPCIONES ---

	@ExceptionHandler(GimnasioNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Response> handleException(GimnasioNotFoundException e) {
		Response response = Response.errorResponse(Response.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SocioNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Response> handleException(SocioNotFoundException e) {
		Response response = Response.errorResponse(Response.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ActividadNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Response> handleException(ActividadNotFoundException e) {
		Response response = Response.errorResponse(Response.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
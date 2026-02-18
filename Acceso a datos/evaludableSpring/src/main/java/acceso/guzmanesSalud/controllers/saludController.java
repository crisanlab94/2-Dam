package acceso.guzmanesSalud.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import acceso.guzmanesSalud.models.ConstantesVitales;
import acceso.guzmanesSalud.models.Paciente;
import acceso.guzmanesSalud.services.PacientesService;





@Controller
@RequestMapping("/salud")
public class saludController {
	
	@Autowired
	private PacientesService pacienteService;
	
	@GetMapping("/") 
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/detalle/{id}")
	public String getPacienteById(@PathVariable("id") Long idPaciente, Model model) {
		Paciente pacieteEncontrado = pacienteService.findPacienteById(idPaciente);
		model.addAttribute("paciente", pacieteEncontrado);
		return "detalle";
	}
	
	
	
	@PostMapping("/agregarConstantes/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> agregaContrante(@PathVariable("id") long idConstante, @RequestBody ConstantesVitales constantesVitales) {
	    Map<String, Object> respuesta = new HashMap<>();
	    HttpStatus estado; // Variable para guardar el estado del resultado

	    try {
	        // Intentamos la operación
	        ConstantesVitales cnuevo = pacienteService.agregarConstantesViatales(idConstante, constantesVitales);
	        
	        respuesta.put("idVacuna", cnuevo.getIdConstantes());
	        respuesta.put("idVacuna", cnuevo.getFechaRegistro());
	        respuesta.put("idVacuna", cnuevo.getFrecuenciaCardiaca());
	        respuesta.put("idVacuna", cnuevo.getPaciente());
	        respuesta.put("idVacuna", cnuevo.getTemperatura());
	        respuesta.put("idVacuna", cnuevo.getTension());
	        
	        estado = HttpStatus.OK; // Todo ha ido bien
	        
	    } catch (RuntimeException e) {
	       
	        respuesta.put("error", e.getMessage());
	        estado = HttpStatus.NOT_FOUND; 
	    }

	    
	    return new ResponseEntity<>(respuesta, estado);
	}
	
}

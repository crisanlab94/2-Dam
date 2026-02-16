package proyectoSpring.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import exception.ActividadNotFoundException;
import proyectoSpring.models.Actividad;
import proyectoSpring.repository.ActividadRepository;


public class ActividadServiceImpl implements ActividadService {
	@Autowired
	private ActividadRepository actividadRepository;


	@Override
	public List<Actividad> findAll() {
		return actividadRepository.findAll();
	}
   
	@Override
	public Set<Actividad> findActividadByCategory(String nombre) {
		return actividadRepository.findByCategory(nombre);
	}

	public Actividad createActividad(Actividad actividad) {
		return actividadRepository.save(actividad);
	}

	// Método para encontrar un producto por ID
    public Actividad findActividadById(long id) {
        Optional<Actividad> optionalActividad = actividadRepository.findById(id);
        return optionalActividad.orElseThrow(() -> new ActividadNotFoundException(id));
    }

	


	
}

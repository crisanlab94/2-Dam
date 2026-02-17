package proyectoSpring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.ActividadNotFoundException;
import exception.GimnasioNotFoundException;
import exception.SocioNotFoundException;
import proyectoSpring.models.Actividad;
import proyectoSpring.models.Gimnasio;
import proyectoSpring.models.Socio;
import proyectoSpring.repository.ActividadRepository;
import proyectoSpring.repository.GimnasioRepository;
import proyectoSpring.repository.SocioRepository;


@Service
public class SocioServiceImpl implements SocioService {
	
	@Autowired
	private SocioRepository socioRepository;

	@Autowired
	private GimnasioRepository gimnasioRepository;
	
	@Autowired
	private ActividadRepository actividadRepository;

	@Override
	public List<Socio> findAll() {
		return socioRepository.findAll();
	}
   
	@Override
	public Set<Socio> findByNombre(String nombre) {
		return socioRepository.findByNombre(nombre);
	}

	@Override
	public Socio createSocio(Socio socio) {
		// Procesar Gimnasio (Relación 1:N)
		if (socio.getGimnasio() != null) {
	        Gimnasio gimnasioReal = gimnasioRepository.findById(socio.getGimnasio().getId())
	            .orElseThrow(() -> new GimnasioNotFoundException(socio.getGimnasio().getId()));
	        socio.setGimnasio(gimnasioReal);
	    }

		// Procesar Actividades (Relación N:M)
		if (socio.getActividades() != null && !socio.getActividades().isEmpty()) {
			List<Actividad> actividadesReales = new ArrayList<Actividad>();
			
			for (Actividad act : socio.getActividades()) {
				// Buscamos cada actividad por su ID para que JPA la reconozca como existente
				Actividad actReal = actividadRepository.findById(act.getId())
					.orElseThrow(() -> new ActividadNotFoundException(act.getId()));
				actividadesReales.add(actReal);
			}
			// Sustituimos la lista del JSON por la lista de objetos reales de la BBDD
			socio.setActividades(actividadesReales);
		}

	    return socioRepository.save(socio);
	}

	// Método para encontrar un socio por ID
    public Socio findSocioById(long id) {
        Optional<Socio> optionalSocio = socioRepository.findById(id);
        return optionalSocio.orElseThrow(() -> new SocioNotFoundException(id));
    }

 // Lógica para filtrar morosos
    @Override
    public List<Socio> findMorosos() {
        return socioRepository.findByEstaAlCorrientePagoFalse();
    }

	@Override
	public void actualizarEstadoPago(long id, boolean estado) {
		Socio s = findSocioById(id);
        s.setEstaAlCorrientePago(estado);
        socioRepository.save(s);
		
	}
	
	//Actualizar socio
	@Override
	public Socio updateSocio(long id, Socio socio) {
	    Socio sOriginal = this.findSocioById(id); 
	    
	    // Actualizamos datos básicos
	    sOriginal.setNombre(socio.getNombre());
	    sOriginal.setEstaAlCorrientePago(socio.isEstaAlCorrientePago());

	    // Actualizamos el Gimnasio (Relación 1:N)
	    if (socio.getGimnasio() != null) {
	        Gimnasio gim = gimnasioRepository.findById(socio.getGimnasio().getId()).orElse(null);
	        sOriginal.setGimnasio(gim);
	    }

	    // Actualizamos las Actividades (Relación N:M)
	    // Esto SUSTITUYE las antiguas por las nuevas que envíes en el JSON
	    if (socio.getActividades() != null) {
	        List<Actividad> nuevasActividades = new ArrayList<>();
	        for (Actividad a : socio.getActividades()) {
	            actividadRepository.findById(a.getId()).ifPresent(nuevasActividades::add);
	        }
	        sOriginal.setActividades(nuevasActividades);
	    }

	    return socioRepository.save(sOriginal);
	}
	
	//Borrar socio
	@Override
	public void deleteById(Long id) {
	    
	    Socio socioEliminar = findSocioById(id);
	    this.socioRepository.delete(socioEliminar);
	}

	

}

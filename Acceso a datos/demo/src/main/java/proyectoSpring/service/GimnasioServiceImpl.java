package proyectoSpring.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GimnasioNotFoundException;
import proyectoSpring.models.Gimnasio;
import proyectoSpring.repository.GimnasioRepository;

@Service
public class GimnasioServiceImpl implements GimnasioService {
	
	@Autowired
	private GimnasioRepository gimnasioRepository;


	@Override
	public List<Gimnasio> findAll() {
		return gimnasioRepository.findAll();
	}
   
	@Override
	public Set<Gimnasio> findGimnasioByNombre(String nombre) {
		return gimnasioRepository.findByNombre(nombre);
	}

	public Gimnasio createGimnasio(Gimnasio gimnasio) {
		return gimnasioRepository.save(gimnasio);
	}

	// Método para encontrar un gimnasio por ID
    public Gimnasio findGimnasioById(long id) {
        Optional<Gimnasio> optionalGimnasio = gimnasioRepository.findById(id);
        return optionalGimnasio.orElseThrow(() -> new GimnasioNotFoundException(id));
    }

	

}

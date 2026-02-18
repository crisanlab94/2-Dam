package acceso.guzmanesSalud.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acceso.guzmanesSalud.models.ConstantesVitales;
import acceso.guzmanesSalud.models.Paciente;
import acceso.guzmanesSalud.repositories.ConstantesVitalesRepository;
import acceso.guzmanesSalud.repositories.PacientesRepository;
import exceptions.PacienteNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class PacientesServiceImpl implements PacientesService {
	@Autowired
	private PacientesRepository pacientesRepository;
	
	@Autowired
	private ConstantesVitalesRepository constantesRepository;

	
	@Override
	public Paciente findPacienteById(long idPaciente) {
		Optional<Paciente> optionalPaciente = pacientesRepository.findById(idPaciente);
        return optionalPaciente.orElseThrow(() -> new PacienteNotFoundException(idPaciente));
		
	}


	
	@Override
	public Paciente crearPaciente(Paciente paciente) {
		

	    Paciente resultado = pacientesRepository.findPacienteById(paciente.getIdPaciente());

	
	    if (resultado == null) {
	        resultado = pacientesRepository.save(paciente);
	    }

	 
	    return resultado;
	    
	   
	}








	@Override
	public ConstantesVitales crearConstante(ConstantesVitales constantesVitales) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ConstantesVitales agregarConstantesViatales(long idConstantes, ConstantesVitales constantes) {
		// TODO Auto-generated method stub
		return null;
	}





	
	@Override
	public ConstantesVitales agregarConstantesViatles(long idConstantes, ConstantesVitales constantes) {
		
	    ConstantesVitales c = constantesRepository.findById(idConstantes)
	            .orElseThrow(() -> new RuntimeException("Error: La cosntante con ID " + idConstante + " no existe."));


	    Paciente p = pacientesRepository.findPacienteById(constantes.getIdConstantes());

	    if (p == null) {
	        
	        c = constantes; 
	        
	       
	        if (p.getConstantesVitales() == null) {
	            p.setConstantesVitales((new ArrayList<>()));
	        }
	    } else {
	       
	    	c.setConstantesVitales(constantes.getIdConstantes());
	    	c.setConstantesVitales(constantes.getFechaRegistro());
	    	c.setConstantesVitales(constantes.getFrecuenciaCardiaca());
	    	c.setConstantesVitales(constantes.getTemperatura());
	    	c.setConstantesVitales(constantes.getTension());
	        
	    }

	 
	    if (!c.getPaciente().contains(constantes)) {
	    	c.getPaciente().add(p);
	      
	      
	    }

	 return constantesRepository.save(c);
	    
	}
	}


	

	
	/*
	@Override
	public ConstantesVitales findConstantesById(long idConstantes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstantesVitales crearConstante(ConstantesVitales constantesVitales) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Paciente findPacienteById(long idPaciente) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ConstantesVitales agregarConstantesViatles(long idConstantes, ConstantesVitales constantes) {
		// TODO Auto-generated method stub
		return null;
	}

*/
	
}

package proyectoSpring.service;

import java.util.List;
import java.util.Set;

import proyectoSpring.models.Gimnasio;



public interface GimnasioService {
	List<Gimnasio>  findAll();
    Set<Gimnasio> findGimnasioByNombre(String nombre);
    public Gimnasio createGimnasio(Gimnasio gimnasio) ;
    public Gimnasio findGimnasioById(long id);
    


    

}

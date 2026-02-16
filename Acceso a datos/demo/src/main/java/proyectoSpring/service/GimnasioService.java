package proyectoSpring.service;

import java.util.List;
import java.util.Set;

import proyectoSpring.models.Gimnasio;



public interface GimnasioService {
	List<Gimnasio>  findAll();
    Set<Gimnasio> findGimnasioByCategory(String nombre);
    public Gimnasio createGimansio(Gimnasio gimnasio) ;
    public Gimnasio findGimnasioById(long id);
    


    

}

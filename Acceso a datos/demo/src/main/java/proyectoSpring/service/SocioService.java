package proyectoSpring.service;

import java.util.List;
import java.util.Set;

import proyectoSpring.models.Socio;

public interface SocioService {
    
    List<Socio>  findAll();
    Set<Socio> findSocioByCategory(String category);
    public Socio createSocio(Socio socio) ;
    public Socio findSocioById(long id);
    

}

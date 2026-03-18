package proyectoSpringSecurity.service;

import java.util.List;
import java.util.Set;

import proyectoSpringSecurity.models.Socio;

public interface SocioService {
    
    List<Socio>  findAll();
    Set<Socio> findByNombre(String category);
    public Socio createSocio(Socio socio) ;
    public Socio findSocioById(long id);
    
    //Socios que no pagan
    List<Socio> findMorosos(); 
    void actualizarEstadoPago(long id, boolean estado);
    //Actualiza socio
    public Socio updateSocio(long id, Socio socio); 
    
    void deleteById(Long id);
}

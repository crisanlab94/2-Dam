package proyectoSpringSecurity.service;

import java.util.List;
import java.util.Set;

import proyectoSpringSecurity.models.Actividad;

public interface ActividadService {
    List<Actividad>  findAll();
    Set<Actividad> findActividadByNombre(String category);
    public Actividad createActividad(Actividad actividad) ;
    public Actividad findActividadById(long id);
  

}

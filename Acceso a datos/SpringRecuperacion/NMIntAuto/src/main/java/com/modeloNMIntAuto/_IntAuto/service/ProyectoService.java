package com.modeloNMIntAuto._IntAuto.service;



import java.util.List;

import com.modeloNMIntAuto._IntAuto.modelo.Empleado;
import com.modeloNMIntAuto._IntAuto.modelo.Proyecto;


public interface ProyectoService {
    // Registros
    Empleado registrarEmpleado(Empleado e);
    Proyecto registrarProyecto(Proyecto p);
    
    // Gestión N:M
    void asignarEmpleadoAProyecto(int idEmpleado, int idProyecto);
    
    // Búsquedas por ID
    Empleado findEmpleadoById(int id);
    Proyecto findProyectoById(int id);

    // Filtros Complejos
    List<Empleado> buscarEmpleadosPorProyecto(String titulo);
    List<Proyecto> buscarProyectosDeEmpleado(String nombreEmpleado);
}

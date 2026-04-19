package com.modeloNMIntAuto._IntAuto.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modeloNMIntAuto._IntAuto.modelo.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findById(Integer id);
    List<Empleado> findAll();
    Empleado save(Empleado e);
    
    // Filtro complejo: Empleados que trabajan en un proyecto con X título
    List<Empleado> findByProyectosTitulo(String titulo);
    
    // Empleados que trabajan en proyectos de prioridad alta
    List<Empleado> findByProyectosPrioridadAltaTrue();
}

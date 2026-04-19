package com.modeloNMIntAuto._IntAuto.repositorio;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modeloNMIntAuto._IntAuto.modelo.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    Optional<Proyecto> findById(Integer id);
    List<Proyecto> findAll();
    Proyecto save(Proyecto p);

    // Filtro complejo: Proyectos donde trabaja un empleado específico por nombre
    List<Proyecto> findByEmpleadosNombreContaining(String nombre);
    
    // Proyectos que finalizan antes de una fecha
    List<Proyecto> findByFechaEntregaBefore(LocalDate fecha);
}

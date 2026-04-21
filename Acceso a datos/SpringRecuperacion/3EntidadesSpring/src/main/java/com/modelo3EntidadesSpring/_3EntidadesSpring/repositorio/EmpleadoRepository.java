package com.modelo3EntidadesSpring._3EntidadesSpring.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Métodos que hereda de JpaRepository (para tenerlos claros)
    Optional<Empleado> findById(Long id);
    List<Empleado> findAll();
    Empleado save(Empleado e);
    void deleteById(Long id);

    // Filtros personalizados
    List<Empleado> findByBecadoTrue();
    List<Empleado> findByDepartamentoNombre(String nombreDept);
}
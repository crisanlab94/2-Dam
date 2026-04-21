package com.modelo3EntidadesSpring._3EntidadesSpring.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    // --- MÉTODOS HEREDADOS ---
    Optional<Departamento> findByIdDept(Long idDept);
    List<Departamento> findAll();
    Departamento save(Departamento d);
    void deleteByIdDept(Long idDept);

    // --- FILTRO ESPECÍFICO ---
    Optional<Departamento> findByNombre(String nombre);
}

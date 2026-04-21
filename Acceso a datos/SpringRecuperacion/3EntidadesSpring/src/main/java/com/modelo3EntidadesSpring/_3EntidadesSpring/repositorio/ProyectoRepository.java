package com.modelo3EntidadesSpring._3EntidadesSpring.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    // --- MÉTODOS HEREDADOS ---
    Optional<Proyecto> findByIdProy(Long idProy);
    List<Proyecto> findAll();
    Proyecto save(Proyecto p);
    void deleteByIdProy(Long idProy);

    // --- FILTROS ---
    List<Proyecto> findByActivoTrue();
    List<Proyecto> findByPresupuestoGreaterThan(double cantidad);
}

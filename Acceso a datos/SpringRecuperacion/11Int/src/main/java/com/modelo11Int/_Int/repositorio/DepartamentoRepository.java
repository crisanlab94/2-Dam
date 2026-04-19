package com.modelo11Int._Int.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo11Int._Int.modelo.Departamento;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    // 1. Búsqueda por ID (Clave Primaria Manual)
    Optional<Departamento> findById(Integer id);

    // 2. Búsqueda por nombre del departamento
    List<Departamento> findByNombreDeptoContaining(String trozo);

    // 3. Filtros por presupuesto
    List<Departamento> findByPresupuestoGreaterThan(double cantidad);
    List<Departamento> findByPresupuestoBetween(double min, double max);

    // 4. Búsqueda por el ID del Director (Relación 1:1)
    Optional<Departamento> findByDirectorId(int idDirector);
}

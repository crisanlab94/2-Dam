package com.modelo1NString._String.repositorio;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo1NString._String.modelo.Colegio;

@Repository
public interface ColegioRepository extends JpaRepository<Colegio, String> {
    // Métodos heredados declarados explícitamente
    Optional<Colegio> findById(String id);
    List<Colegio> findAll();
    Colegio save(Colegio c);
    void deleteById(String id);
    boolean existsById(String id);

    // Filtros complejos
    List<Colegio> findByPublicoTrueAndFechaFundacionAfter(LocalDate fecha);
    List<Colegio> findByNombreContainingOrderByFechaFundacionDesc(String trozo);
}

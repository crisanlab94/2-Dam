package com.modelo1NInt._Int.repositorio;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo1NInt._Int.modelo.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Optional<Hospital> findById(Integer id);
    List<Hospital> findAll();
    Hospital save(Hospital h);
    boolean existsById(Integer id);
    void deleteById(Integer id);

    // Filtro complejo: Hospitales públicos con nombre similar
    List<Hospital> findByPublicoTrueAndNombreContaining(String trozo);
}

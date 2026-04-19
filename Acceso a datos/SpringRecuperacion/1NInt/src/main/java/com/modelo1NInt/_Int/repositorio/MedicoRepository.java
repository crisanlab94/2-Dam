package com.modelo1NInt._Int.repositorio;



import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo1NInt._Int.modelo.Especialidad;
import com.modelo1NInt._Int.modelo.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    Optional<Medico> findById(Integer id);
    List<Medico> findAll();
    Medico save(Medico m);
    boolean existsById(Integer id);

    // FILTROS COMPLEJOS
    List<Medico> findByEspecialidadAndActivoTrue(Especialidad esp);
    List<Medico> findByFechaContratacionBetween(LocalDate inicio, LocalDate fin);
    
    // Navegación PRO: Médicos de hospitales que NO son públicos
    List<Medico> findByHospitalPublicoFalse();
}

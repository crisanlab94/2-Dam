package com.modelo11IntAuto._IntAuto.repositorio;



import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.modelo11IntAuto._IntAuto.modelo.Expediente;
import com.modelo11IntAuto._IntAuto.modelo.Paciente;

/**
 * R1: Repositorio para la entidad Expediente.
 */
@Repository 
public interface ExpedienteRepository extends JpaRepository<Expediente, Long> {

    /**
     * 1. Buscar por ID:
     * Cabecera explícita para demostrar manejo de Optional.
     */
    Optional<Expediente> findById(Long id);

    /**
     * 2. Buscar por el objeto relacionado (Paciente):
     * Este es MUY común en exámenes. Como es 1:1, te devuelve un solo Expediente.
     */
    Expediente findByPaciente(Paciente paciente);

    /**
     * 3. Buscar por un valor exacto de Double (Peso):
     * Para buscar a todos los que pesen exactamente X.
     */
    List<Expediente> findByPeso(double peso);

    /**
     * 4. Buscar por comparación (Menor que):
     * Muy útil para filtros de "pacientes con altura menor a...".
     */
    List<Expediente> findByAlturaLessThan(double alturaMaxima);

    /**
     * 5. Buscar por fecha posterior (After):
     * Filtra expedientes creados después de una fecha concreta.
     */
    List<Expediente> findByFechaAperturaAfter(LocalDateTime fecha);
    
    /**
     * 6. Buscar por rango (Between):
     * Para buscar expedientes cuyos pacientes tengan un peso entre dos valores.
     */
    List<Expediente> findByPesoBetween(double pesoMin, double pesoMax);
}
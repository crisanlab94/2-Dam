package com.modelo11IntAuto._IntAuto.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; // Necesario para el Optional

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo11IntAuto._IntAuto.modelo.Expediente;
import com.modelo11IntAuto._IntAuto.modelo.Genero;
import com.modelo11IntAuto._IntAuto.modelo.Paciente;
import com.modelo11IntAuto._IntAuto.repositorio.ExpedienteRepository;
import com.modelo11IntAuto._IntAuto.repositorio.PacienteRepository;

import exceptions.ExpedienteNotFoundException;
import exceptions.PacienteNotFoundException;



@Service 
public class PacienteServiceImpl implements PacienteService {

    // Logger obligatorio
    private static final Logger logger = LoggerFactory.getLogger(PacienteServiceImpl.class);

    @Autowired 
    private PacienteRepository pRepo;

    @Autowired 
    private ExpedienteRepository eRepo;

    // --- MÉTODOS DE BÚSQUEDA POR ID (LECTURA) ---

    @Override
    public Paciente findPacienteById(long id) {
        logger.info("Buscando paciente con ID: {}", id);
        Optional<Paciente> optionalPaciente = pRepo.findById(id);
        return optionalPaciente.orElseThrow(() -> new PacienteNotFoundException(id));
    }

    @Override
    public Expediente findExpedienteById(long id) {
        logger.info("Buscando expediente con ID: {}", id);
        Optional<Expediente> optionalExpediente = eRepo.findById(id);
        return optionalExpediente.orElseThrow(() -> new ExpedienteNotFoundException( id));
    }

    // --- MÉTODOS DE CREACIÓN Y ASOCIACIÓN ---

    @Override
    public Paciente crearPaciente(Paciente p) {
        logger.info("Creando paciente: {}", p.getNombre());
        if (p.getId() != null && pRepo.existsById(p.getId())) {
            throw new RuntimeException("El paciente con ID " + p.getId() + " ya existe.");
        }
        return pRepo.save(p);
    }

    @Override
    public Paciente agregarExpediente(long idPaciente, Expediente exp) {
        logger.info("Agregando expediente al paciente ID: {}", idPaciente);
        Paciente p = this.findPacienteById(idPaciente);
        exp.setPaciente(p);
        eRepo.save(exp);
        return p;
    }

    // --- MÉTODOS DE MODIFICACIÓN (UPDATE) ---

    @Override
    public Paciente modificarPaciente(Paciente p) {
        logger.info("Modificando paciente con ID: {}", p.getId());
        if (p.getId() == null || !pRepo.existsById(p.getId())) {
            throw new PacienteNotFoundException(p.getId() != null ? p.getId() : 0);
        }
        return pRepo.save(p);
    }

    @Override
    public Expediente modificarExpediente(Expediente exp) {
        logger.info("Modificando expediente con ID: {}", exp.getId());
        if (exp.getId() == null || !eRepo.existsById(exp.getId())) {
            throw new RuntimeException("No se puede modificar: El expediente no existe.");
        }
        return eRepo.save(exp);
    }

    // --- MÉTODOS DE ELIMINACIÓN (DELETE) ---

    @Override
    public void eliminarPaciente(long id) {
        logger.info("Eliminando paciente con ID: {}", id);
        if (!pRepo.existsById(id)) {
            throw new PacienteNotFoundException(id);
        }
        pRepo.deleteById(id);
    }

    @Override
    public void eliminarExpediente(long id) {
        logger.info("Eliminando expediente con ID: {}", id);
        if (!eRepo.existsById(id)) {
            throw new RuntimeException("No existe el expediente con ID: " + id);
        }
        eRepo.deleteById(id);
    }

    // --- MÉTODOS DE FILTRADO PERSONALIZADO (Naming Convention) ---

    @Override
    public List<Paciente> buscarPorNombre(String nombre) {
        logger.info("Buscando pacientes por nombre: {}", nombre);
        return pRepo.findByNombre(nombre);
    }

    @Override
    public List<Paciente> buscarPorAsegurado(boolean asegurado) {
        logger.info("Filtrando pacientes por estado de asegurado: {}", asegurado);
        return pRepo.findByAsegurado(asegurado);
    }

    @Override
    public List<Paciente> buscarPorGenero(Genero genero) {
        logger.info("Filtrando pacientes por género: {}", genero);
        return pRepo.findByGenero(genero);
    }

    @Override
    public List<Paciente> buscarPorFechaNacimiento(LocalDate fecha) {
        logger.info("Buscando pacientes nacidos en: {}", fecha);
        return pRepo.findByFechaNacimiento(fecha);
    }

    @Override
    public Expediente buscarExpedientePorPaciente(Paciente p) {
        logger.info("Buscando expediente asociado al paciente: {}", p.getNombre());
        return eRepo.findByPaciente(p);
    }

    @Override
    public List<Expediente> buscarPorPeso(double peso) {
        logger.info("Filtrando expedientes por peso exacto: {}", peso);
        return eRepo.findByPeso(peso);
    }

    @Override
    public List<Expediente> buscarPorRangoDePeso(double min, double max) {
        logger.info("Filtrando expedientes por rango de peso: {} - {}", min, max);
        return eRepo.findByPesoBetween(min, max);
    }

    @Override
    public List<Expediente> buscarExpedientesRecientes(LocalDateTime desde) {
        logger.info("Buscando expedientes abiertos después de: {}", desde);
        return eRepo.findByFechaAperturaAfter(desde); 
    }
}
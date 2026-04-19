package com.modelo1NString._String.service;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo1NString._String.modelo.Colegio;
import com.modelo1NString._String.modelo.Estudiante;
import com.modelo1NString._String.modelo.Genero;
import com.modelo1NString._String.repositorio.ColegioRepository;
import com.modelo1NString._String.repositorio.EstudianteRepository;

// IMPORTS DE TUS EXCEPCIONES
import exceptions.ColegioNotFoundException;
import exceptions.EstudianteNotFoundException;
import exceptions.DuplicateIdException;

@Service
public class ColegioServiceImpl implements ColegioService {

    private static final Logger logger = LoggerFactory.getLogger(ColegioServiceImpl.class);

    @Autowired private ColegioRepository colRepo;
    @Autowired private EstudianteRepository estRepo;

    @Override
    public Colegio findColegioById(String id) {
        logger.info("Service: Buscando colegio {}", id);
        return colRepo.findById(id)
                .orElseThrow(() -> new ColegioNotFoundException(id)); 
    }

    @Override
    public Estudiante findEstudianteById(String id) {
        logger.info("Service: Buscando estudiante {}", id);
        return estRepo.findById(id)
                .orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    @Override
    public Colegio registrarColegio(Colegio c) {
        logger.info("Service: Intentando registrar colegio {}", c.getCodigoCentro());
        if (colRepo.existsById(c.getCodigoCentro())) {
            throw new DuplicateIdException(c.getCodigoCentro());
        }
        return colRepo.save(c);
    }

    @Override
    public Estudiante registrarEstudiante(Estudiante e) {
        logger.info("Service: Intentando registrar estudiante {}", e.getDni());
        if (estRepo.existsById(e.getDni())) {
            throw new DuplicateIdException(e.getDni());
        }
        return estRepo.save(e);
    }

    @Override
    public void asignarEstudiante(String dni, String codigoCentro) {
        logger.info("Service: Asignando alumno {} al centro {}", dni, codigoCentro);
        
        // Reutilizamos findById que ya lanza las excepciones correspondientes
        Colegio col = this.findColegioById(codigoCentro);
        Estudiante est = this.findEstudianteById(dni);
        
        est.setColegio(col);
        estRepo.save(est);
    }

    @Override
    public List<Estudiante> buscarMujeresActivas() {
        logger.info("Service: Filtrando mujeres activas");
        return estRepo.findByActivoTrueAndGenero(Genero.FEMENINO);
    }

    @Override
    public List<Estudiante> buscarIngresosRango(LocalDate inicio, LocalDate fin) {
        logger.info("Service: Buscando ingresos entre {} y {}", inicio, fin);
        return estRepo.findByFechaIngresoBetween(inicio, fin);
    }

    @Override
    public List<Estudiante> buscarAlumnosEnCentrosPublicos() {
        logger.info("Service: Obteniendo alumnos de colegios públicos");
        return estRepo.findByColegioPublicoTrue();
    }

    @Override
    public List<Colegio> buscarColegiosPublicosNuevos(LocalDate fecha) {
        logger.info("Service: Colegios públicos fundados tras {}", fecha);
        return colRepo.findByPublicoTrueAndFechaFundacionAfter(fecha);
    }
}
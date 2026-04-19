package com.modelo11Int._Int.service;


import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo11Int._Int.modelo.Departamento;
import com.modelo11Int._Int.modelo.Director;
import com.modelo11Int._Int.repositorio.DepartamentoRepository;
import com.modelo11Int._Int.repositorio.DirectorRepository;

import exceptions.DepartamentoNotFoundException;
import exceptions.DirectorNotFoundException;
import exceptions.DuplicateIdException;
import jakarta.transaction.Transactional;

@Service
public class DirectorServiceImpl implements DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

    @Autowired private DirectorRepository dirRepo;
    @Autowired private DepartamentoRepository depRepo;

    // --- REGISTROS ---

    @Override
    public Director registrarDirector(Director d) {
        logger.info("Service: Registrando director ID manual {}", d.getId());
        if (dirRepo.existsById(d.getId())) throw new DirectorNotFoundException("ID Director ya existe.");
        return dirRepo.save(d);
    }

    @Override
    public Departamento registrarDepartamento(Departamento dep) {
        logger.info("Service: Registrando depto ID manual {}", dep.getId());
        if (depRepo.existsById(dep.getId())) throw new DepartamentoNotFoundException("ID Depto ya existe.");
        return depRepo.save(dep);
    }

    @Transactional
    @Override
    public Director asociarDepartamento(int idDirector, Departamento dep) {
        logger.info("Service: Asociando depto {} a director {}", dep.getId(), idDirector);
        Director dir = this.findDirectorById(idDirector);
        if (depRepo.existsById(dep.getId())) throw new DuplicateIdException(idDirector);
        dep.setDirector(dir);
        depRepo.save(dep);
        return dir;
    }

    @Override
    public Director modificarNombre(int id, String nuevoNombre) {
        logger.info("Service: Intentando modificar nombre al ID {}", id);
        
        // 1. Buscamos al director (reutilizamos nuestro findDirectorById para que lance la excepción si no existe)
        Director dir = this.findDirectorById(id);
        
        // 2. Cambiamos el atributo
        dir.setNombre(nuevoNombre);
        
        // 3. Guardamos. JPA hace UPDATE automáticamente al detectar que el ID ya existe.
        logger.info("Service: Nombre actualizado correctamente para el ID {}", id);
        return dirRepo.save(dir);
    }

    @Override
    public void eliminarDirector(int id) {
        logger.info("Service: Solicitando eliminación del director con ID {}", id);
        
        // 1. Comprobamos si existe antes de borrar
        if (!dirRepo.existsById(id)) {
            logger.error("Service: No se puede eliminar. El ID {} no existe", id);
            throw new DirectorNotFoundException(id);
        }
        
        // 2. Borramos
        dirRepo.deleteById(id);
        logger.info("Service: Director {} eliminado de la base de datos", id);
    }
    
    // --- FILTROS DIRECTOR ---

    @Override
    public Director findDirectorById(int id) {
        logger.info("Service: Buscando director {}", id);
        return dirRepo.findById(id).orElseThrow(() -> new DirectorNotFoundException(id));
    }

    @Override
    public List<Director> buscarDirectorPorNombre(String n) { return dirRepo.findByNombre(n); }
    @Override
    public List<Director> buscarDirectorPorNombreContiene(String t) { return dirRepo.findByNombreContaining(t); }
    @Override
    public List<Director> buscarSueldoMayorQue(double s) { return dirRepo.findBySueldoGreaterThan(s); }
    @Override
    public List<Director> buscarSueldoMenorQue(double s) { return dirRepo.findBySueldoLessThan(s); }
    @Override
    public List<Director> buscarSueldoEnRango(double min, double max) { return dirRepo.findBySueldoBetween(min, max); }
    @Override
    public List<Director> buscarNombramientosAntesDe(LocalDate f) { return dirRepo.findByFechaNombramientoBefore(f); }
    @Override
    public List<Director> buscarNombramientosDespuesDe(LocalDate f) { return dirRepo.findByFechaNombramientoAfter(f); }
    
    @Override
    public Director buscarDirectorPorDepto(int idDepto) {
        return dirRepo.findByDepartamentoId(idDepto).orElseThrow(() -> new RuntimeException("No hay director para ese depto"));
    }

    // --- FILTROS DEPARTAMENTO ---

    @Override
    public Departamento findDeptoById(int id) {
        return depRepo.findById(id).orElseThrow(() -> new RuntimeException("Depto no encontrado"));
    }

    @Override
    public List<Departamento> buscarDeptoPorNombreContiene(String t) { return depRepo.findByNombreDeptoContaining(t); }
    @Override
    public List<Departamento> buscarDeptoPresupuestoMayorQue(double p) { return depRepo.findByPresupuestoGreaterThan(p); }
    @Override
    public List<Departamento> buscarDeptoPresupuestoEnRango(double min, double max) { return depRepo.findByPresupuestoBetween(min, max); }

    @Override
    public Departamento buscarDeptoPorDirector(int idDirector) {
        return depRepo.findByDirectorId(idDirector).orElseThrow(() -> new RuntimeException("Director sin depto"));
    }
}

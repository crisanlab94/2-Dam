package com.modeloNMIntAuto._IntAuto.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modeloNMIntAuto._IntAuto.modelo.Empleado;
import com.modeloNMIntAuto._IntAuto.modelo.Proyecto;
import com.modeloNMIntAuto._IntAuto.repositorio.EmpleadoRepository;
import com.modeloNMIntAuto._IntAuto.repositorio.ProyectoRepository;

import exceptions.EmpleadoNotFoundException;
import exceptions.ProyectoNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoServiceImpl.class);

    @Autowired 
    private EmpleadoRepository empRepo;
    
    @Autowired 
    private ProyectoRepository proRepo;

    @Override
    public Empleado findEmpleadoById(int id) {
        logger.info("Service: Buscando empleado con ID {}", id);
        return empRepo.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }

    @Override
    public Proyecto findProyectoById(int id) {
        logger.info("Service: Buscando proyecto con ID {}", id);
        return proRepo.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
    }

    @Override
    public Empleado registrarEmpleado(Empleado e) {
        logger.info("Service: Guardando empleado {}", e.getNombre());
        // Al ser Auto-increment, no validamos existsById porque el ID se genera en el save
        return empRepo.save(e);
    }

    @Override
    public Proyecto registrarProyecto(Proyecto p) {
        logger.info("Service: Guardando proyecto {}", p.getTitulo());
        return proRepo.save(p);
    }

    @Transactional
    @Override
    public void asignarEmpleadoAProyecto(int idEmpleado, int idProyecto) {
        logger.info("Service: Intentando vincular empleado {} al proyecto {}", idEmpleado, idProyecto);
        
        // Reutilizamos los métodos de búsqueda que ya lanzan nuestras excepciones propias
        Empleado e = this.findEmpleadoById(idEmpleado);
        Proyecto p = this.findProyectoById(idProyecto);
        
        // Lógica de asociación N:M
        p.getEmpleados().add(e);
        
        // Guardamos el dueño de la relación
        proRepo.save(p); 
        logger.info("Service: Vinculación completada con éxito.");
    }

    @Override
    public List<Empleado> buscarEmpleadosPorProyecto(String titulo) {
        logger.info("Service: Buscando equipo del proyecto: {}", titulo);
        return empRepo.findByProyectosTitulo(titulo);
    }

    @Override
    public List<Proyecto> buscarProyectosDeEmpleado(String nombre) {
        logger.info("Service: Buscando proyectos de: {}", nombre);
        return proRepo.findByEmpleadosNombreContaining(nombre);
    }
}
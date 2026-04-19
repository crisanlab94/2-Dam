package com.modelo1NIntAuto._IntAuto.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modelo1NIntAuto._IntAuto.modelo.*;
import com.modelo1NIntAuto._IntAuto.repositorio.*;

// IMPORTS DE TUS EXCEPCIONES
import exceptions.EmpresaNotFoundException;
import exceptions.EmpleadoNotFoundException;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private static final Logger logger = LoggerFactory.getLogger(EmpresaServiceImpl.class);

    @Autowired private EmpresaRepository empRepo;
    @Autowired private EmpleadoRepository repoEmp;

    @Override
    public Empresa findEmpresaById(int id) {
        logger.info("Service: Buscando empresa {}", id);
        return empRepo.findById(id)
                .orElseThrow(() -> new EmpresaNotFoundException(id));
    }

    @Override
    public Empleado findEmpleadoById(int id) {
        logger.info("Service: Buscando empleado {}", id);
        return repoEmp.findById(id)
                .orElseThrow(() -> new EmpleadoNotFoundException(id));
    }

    @Override
    public Empresa registrarEmpresa(Empresa e) {
        logger.info("Service: Guardando empresa {}", e.getNombre());
        return empRepo.save(e);
    }

    @Override
    public Empleado registrarEmpleado(Empleado emp) {
        logger.info("Service: Guardando empleado {}", emp.getNombre());
        return repoEmp.save(emp);
    }

    @Override
    public void contratarEmpleado(int idEmpleado, int idEmpresa) {
        logger.info("Service: Contratando empleado {} en empresa {}", idEmpleado, idEmpresa);
        
        // Al usar findById de este mismo Service, ya lanzamos las excepciones propias
        Empresa em = this.findEmpresaById(idEmpresa);
        Empleado el = this.findEmpleadoById(idEmpleado);
        
        el.setEmpresa(em);
        repoEmp.save(el);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorEmpresa(int idEmpresa) {
        return repoEmp.findByEmpresaId(idEmpresa);
    }

    @Override
    public List<Empleado> buscarDirectivos() {
        return repoEmp.findByPuesto(Puesto.DIRECTOR);
    }

    @Override
    public List<Empresa> buscarMultinacionales() {
        return empRepo.findByEsMultinacionalTrue();
    }
}
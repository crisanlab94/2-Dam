package com.modelo3EntidadesSpring._3EntidadesSpring.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Departamento;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Empleado;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Proyecto;
import com.modelo3EntidadesSpring._3EntidadesSpring.repositorio.DepartamentoRepository;
import com.modelo3EntidadesSpring._3EntidadesSpring.repositorio.EmpleadoRepository;
import com.modelo3EntidadesSpring._3EntidadesSpring.repositorio.ProyectoRepository;

import exceptions.DepartamentoNotFoundException;
import exceptions.EmpleadoNotFoundException;
import exceptions.ProyectoNotFoundException;


@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired private EmpleadoRepository empRepo;
    @Autowired private ProyectoRepository proyRepo;
    @Autowired private DepartamentoRepository deptRepo;

    @Override
    public Empleado crearEmpleado(Empleado e) {
        return empRepo.save(e);
    }

    @Override
    public Departamento crearDepartamento(Departamento d) {
        return deptRepo.save(d);
    }

    @Override
    public Proyecto crearProyecto(Proyecto p) {
        return proyRepo.save(p);
    }

    @Transactional
    @Override
    public void asignarEmpleadoADepartamento(Long idEmp, Long idDept) {
        Empleado e = empRepo.findById(idEmp).orElseThrow(() -> new EmpleadoNotFoundException("No existe el empleado"));
        Departamento d = deptRepo.findByIdDept(idDept).orElseThrow(() -> new DepartamentoNotFoundException("No existe el depto"));
        e.setDepartamento(d);
        d.getEmpleados().add(e);
        empRepo.save(e);
    }

    @Transactional
    @Override
    public void vincularEmpleadoAProyecto(Long idEmp, Long idProy) {
        Empleado e = empRepo.findById(idEmp).orElseThrow(() -> new EmpleadoNotFoundException("No existe"));
        Proyecto p = proyRepo.findByIdProy(idProy).orElseThrow(() -> new ProyectoNotFoundException("No existe"));
        e.getProyectos().add(p);
        p.getEmpleados().add(e);
        empRepo.save(e);
    }
    
    @Override
    public void eliminarEmpleado(Long id) {
        Empleado e = empRepo.findById(id).orElseThrow(() -> new EmpleadoNotFoundException("No existe"));
        
        // LIMPIEZA MANUAL N:M (Sin Cascade)
        for (Proyecto p : e.getProyectos()) {
            p.getEmpleados().remove(e);
        }
        e.getProyectos().clear();
        
        // LIMPIEZA MANUAL 1:N
        if (e.getDepartamento() != null) {
            e.getDepartamento().getEmpleados().remove(e);
        }
        
        empRepo.save(e);
        empRepo.delete(e);
    }

    @Transactional
    @Override
    public void eliminarProyecto(Long id) {
        Proyecto p = proyRepo.findByIdProy(id).orElseThrow(() -> new DepartamentoNotFoundException("No existe"));
        for (Empleado e : p.getEmpleados()) {
            e.getProyectos().remove(p);
            empRepo.save(e);
        }
        proyRepo.delete(p);
    }

    @Transactional
    @Override
    public void eliminarDepartamento(Long id) {
        Departamento d = deptRepo.findByIdDept(id).orElseThrow(() -> new DepartamentoNotFoundException("No existe"));
        // Ponemos a null la FK de los empleados asociados
        for (Empleado e : d.getEmpleados()) {
            e.setDepartamento(null);
            empRepo.save(e);
        }
        deptRepo.delete(d);
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        return empRepo.findById(id).orElseThrow(() -> new EmpleadoNotFoundException("No encontrado"));
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empRepo.findAll();
    }

    @Override
    public List<Departamento> obtenerTodosLosDepartamentos() {
        return deptRepo.findAll();
    }
}

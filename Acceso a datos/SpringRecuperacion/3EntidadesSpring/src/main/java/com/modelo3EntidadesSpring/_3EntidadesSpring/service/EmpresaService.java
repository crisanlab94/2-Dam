package com.modelo3EntidadesSpring._3EntidadesSpring.service;

import java.util.List;

import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Departamento;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Empleado;
import com.modelo3EntidadesSpring._3EntidadesSpring.modelo.Proyecto;

public interface EmpresaService {

    // --- MÉTODOS DE CREACIÓN ---
    Empleado crearEmpleado(Empleado e);
    Departamento crearDepartamento(Departamento d);
    Proyecto crearProyecto(Proyecto p);

    // --- MÉTODOS DE ASOCIACIÓN ---
    void asignarEmpleadoADepartamento(Long idEmp, Long idDept);
    void vincularEmpleadoAProyecto(Long idEmp, Long idProy);

    // --- MÉTODOS DE ELIMINACIÓN ---
    void eliminarEmpleado(Long id);
    void eliminarProyecto(Long id);
    void eliminarDepartamento(Long id);

    // --- MÉTODOS DE CONSULTA ---
    Empleado buscarEmpleadoPorId(Long id);
    List<Empleado> obtenerTodosLosEmpleados();
    List<Departamento> obtenerTodosLosDepartamentos();
}
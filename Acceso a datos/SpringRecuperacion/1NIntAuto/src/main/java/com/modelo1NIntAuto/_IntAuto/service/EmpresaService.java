package com.modelo1NIntAuto._IntAuto.service;



import java.util.List;

import com.modelo1NIntAuto._IntAuto.modelo.Empleado;
import com.modelo1NIntAuto._IntAuto.modelo.Empresa;


public interface EmpresaService {
    // Búsquedas por ID
    Empresa findEmpresaById(int id);
    Empleado findEmpleadoById(int id);

    // Registros
    Empresa registrarEmpresa(Empresa e);
    Empleado registrarEmpleado(Empleado emp);
    
    // Gestión 1:N
    void contratarEmpleado(int idEmpleado, int idEmpresa);
    List<Empleado> buscarEmpleadosPorEmpresa(int idEmpresa);
    
    // Filtros complejos
    List<Empleado> buscarDirectivos();
    List<Empresa> buscarMultinacionales();
}

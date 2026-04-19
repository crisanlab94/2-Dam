package com.modelo11Int._Int.service;



import java.time.LocalDate;
import java.util.List;

import com.modelo11Int._Int.modelo.Departamento;
import com.modelo11Int._Int.modelo.Director;


public interface DirectorService {
    // Registros
    Director registrarDirector(Director d);
    Departamento registrarDepartamento(Departamento dep);
    Director asociarDepartamento(int idDirector, Departamento dep);

    // Filtros Director
    Director findDirectorById(int id);
    List<Director> buscarDirectorPorNombre(String nombre);
    List<Director> buscarDirectorPorNombreContiene(String trozo);
    List<Director> buscarSueldoMayorQue(double sueldo);
    List<Director> buscarSueldoMenorQue(double sueldo);
    List<Director> buscarSueldoEnRango(double min, double max);
    List<Director> buscarNombramientosAntesDe(LocalDate fecha);
    List<Director> buscarNombramientosDespuesDe(LocalDate fecha);
    Director buscarDirectorPorDepto(int idDepto);
    Director modificarNombre(int id, String nuevoNombre);

 // Elimina un director por su ID
 void eliminarDirector(int id);

    // Filtros Departamento
    Departamento findDeptoById(int id);
    List<Departamento> buscarDeptoPorNombreContiene(String trozo);
    List<Departamento> buscarDeptoPresupuestoMayorQue(double presupuesto);
    List<Departamento> buscarDeptoPresupuestoEnRango(double min, double max);
    Departamento buscarDeptoPorDirector(int idDirector);
}

package com.modelo3EntidadesSpring._3EntidadesSpring.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Lado 1

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Long Autogenerado
    private Long idDept;
    private String nombre;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
    private List<Empleado> empleados = new ArrayList<>();

    public Departamento() {}
    public Departamento(Long idDept, String nombre) {
        this.idDept = idDept;
        this.nombre = nombre;
    }

    // Getters, Setters y toString
    public Long getIdDept() { return idDept; }
    public void setIdDept(Long idDept) { this.idDept = idDept; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }

    @Override
    public String toString() {
        return "Departamento [id=" + idDept + ", nombre=" + nombre + "]";
    }
}
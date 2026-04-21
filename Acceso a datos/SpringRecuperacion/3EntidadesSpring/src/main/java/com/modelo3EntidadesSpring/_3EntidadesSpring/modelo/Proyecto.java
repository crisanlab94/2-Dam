package com.modelo3EntidadesSpring._3EntidadesSpring.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Lado no dueño de 1:N y de M:N 
@Entity
@Table(name = "proyecto")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Long Autogenerado
    private Long idProy;
    private String nombre;
    private double presupuesto;
    private boolean activo;

    @ManyToMany(mappedBy = "proyectos", fetch = FetchType.EAGER)
    private List<Empleado> empleados = new ArrayList<>();

    public Proyecto() {}
    public Proyecto(Long idProy, String nombre, double presupuesto, boolean activo) {
        this.idProy = idProy;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.activo = activo;
    }

    // Getters, Setters y toString
    public Long getIdProy() { return idProy; }
    public void setIdProy(Long idProy) { this.idProy = idProy; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }

    @Override
    public String toString() {
        return "Proyecto [id=" + idProy + ", nombre=" + nombre + "]";
    }
}
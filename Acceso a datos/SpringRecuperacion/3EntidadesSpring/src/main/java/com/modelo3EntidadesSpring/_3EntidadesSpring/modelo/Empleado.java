package com.modelo3EntidadesSpring._3EntidadesSpring.modelo;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Lado dueño de M:N  y de 1:N
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Long Autogenerado
    private Long idEmp;
    
    private String nombre;
    private double salario;
    private boolean becado;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "id_dept")
    private Departamento departamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "empleado_proyecto",
        joinColumns = @JoinColumn(name = "id_emp"),
        inverseJoinColumns = @JoinColumn(name = "id_proy")
    )
    private List<Proyecto> proyectos = new ArrayList<>();

    public Empleado() {}
    public Empleado(Long idEmp, String nombre, double salario, boolean becado, Cargo cargo, LocalDate fechaContratacion) {
        this.idEmp = idEmp;
        this.nombre = nombre;
        this.salario = salario;
        this.becado = becado;
        this.cargo = cargo;
        this.fechaContratacion = fechaContratacion;
    }

    // Getters, Setters y toString
    public Long getIdEmp() { return idEmp; }
    public void setIdEmp(Long idEmp) { this.idEmp = idEmp; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public boolean isBecado() { return becado; }
    public void setBecado(boolean becado) { this.becado = becado; }
    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public List<Proyecto> getProyectos() { return proyectos; }
    public void setProyectos(List<Proyecto> proyectos) { this.proyectos = proyectos; }

    @Override
    public String toString() {
        return "Empleado [id=" + idEmp + ", nombre=" + nombre + ", cargo=" + cargo + "]";
    }
}
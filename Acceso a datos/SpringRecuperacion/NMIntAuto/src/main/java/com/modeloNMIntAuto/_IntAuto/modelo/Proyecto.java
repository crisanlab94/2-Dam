package com.modeloNMIntAuto._IntAuto.modelo;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

//Lado Dueño
@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private boolean prioridadAlta;
    
    @Enumerated(EnumType.STRING)
    private EstadoProyecto estado;
    
    private LocalDate fechaEntrega;

    @ManyToMany
    @JoinTable(
        name = "empleado_proyecto",
        joinColumns = @JoinColumn(name = "id_proyecto"),
        inverseJoinColumns = @JoinColumn(name = "id_empleado")
    )
    private List<Empleado> empleados = new ArrayList<>();

    public Proyecto() {}
    public Proyecto(String titulo, boolean prioridadAlta, EstadoProyecto estado, LocalDate entrega) {
        this.titulo = titulo;
        this.prioridadAlta = prioridadAlta;
        this.estado = estado;
        this.fechaEntrega = entrega;
    }

    @Override
    public String toString() {
        return "Proyecto [id=" + id + ", titulo=" + titulo + ", estado=" + estado + "]";
    }

    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public boolean isPrioridadAlta() { return prioridadAlta; }
    public void setPrioridadAlta(boolean prioridadAlta) { this.prioridadAlta = prioridadAlta; }
    public EstadoProyecto getEstado() { return estado; }
    public void setEstado(EstadoProyecto estado) { this.estado = estado; }
    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }
}

package com.modelo1NIntAuto._IntAuto.modelo;



import jakarta.persistence.*;
import java.time.LocalDate;


//Lado N

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private double salario;
    private boolean activo;
    
    @Enumerated(EnumType.STRING)
    private Puesto puesto;
    
    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    public Empleado() {}

    public Empleado(String nombre, double salario, boolean activo, Puesto puesto, LocalDate fecha) {
        this.nombre = nombre;
        this.salario = salario;
        this.activo = activo;
        this.puesto = puesto;
        this.fechaContratacion = fecha;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", puesto=" + puesto + "]";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public Puesto getPuesto() { return puesto; }
    public void setPuesto(Puesto puesto) { this.puesto = puesto; }
    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}

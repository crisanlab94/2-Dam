package com.modelo1NIntAuto._IntAuto.modelo;



import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//Lado 1
@Entity
@Table(name = "empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;
    private String sedeCentral;
    private double facturacionMillones;
    private boolean esMultinacional;
    private LocalDate fechaApertura;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
    private List<Empleado> empleados = new ArrayList<>();

    public Empresa() {}

    // Constructor sin ID para registros nuevos
    public Empresa(String nombre, String sedeCentral, double facturacion, boolean esMultinacional, LocalDate fecha) {
        this.nombre = nombre;
        this.sedeCentral = sedeCentral;
        this.facturacionMillones = facturacion;
        this.esMultinacional = esMultinacional;
        this.fechaApertura = fecha;
    }

    @Override
    public String toString() {
        return "Empresa [id=" + id + ", nombre=" + nombre + ", sede=" + sedeCentral + "]";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSedeCentral() { return sedeCentral; }
    public void setSedeCentral(String sedeCentral) { this.sedeCentral = sedeCentral; }
    public double getFacturacionMillones() { return facturacionMillones; }
    public void setFacturacionMillones(double facturacionMillones) { this.facturacionMillones = facturacionMillones; }
    public boolean isEsMultinacional() { return esMultinacional; }
    public void setEsMultinacional(boolean esMultinacional) { this.esMultinacional = esMultinacional; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }
    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }
}

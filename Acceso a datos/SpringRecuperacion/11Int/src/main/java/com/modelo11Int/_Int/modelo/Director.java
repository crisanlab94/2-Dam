package com.modelo11Int._Int.modelo;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "director")
public class Director {

    @Id // ID Manual (Sin GeneratedValue)
    private int id;
    
    private String nombre;
    private double sueldo;
    private LocalDate fechaNombramiento;

    // Relación 1:1 Inversa. MappedBy indica que 'director' en Departamento manda.
    @OneToOne(mappedBy = "director", cascade = CascadeType.ALL)
    private Departamento departamento;

    // 1. Constructor Vacío (Obligatorio JPA)
    public Director() {}

    // 2. Constructor Completo
    public Director(int id, String nombre, double sueldo, LocalDate fechaNombramiento) {
        this.id = id;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.fechaNombramiento = fechaNombramiento;
    }

    // 3. toString (Evitamos bucle infinito con departamento)
    @Override
    public String toString() {
        return "Director [id=" + id + ", nombre=" + nombre + ", sueldo=" + sueldo + "]";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }
    public LocalDate getFechaNombramiento() { return fechaNombramiento; }
    public void setFechaNombramiento(LocalDate fechaNombramiento) { this.fechaNombramiento = fechaNombramiento; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
}

package com.modelo1NString._String.modelo;



import jakarta.persistence.*;
import java.time.LocalDate;

//Lado N
@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    private String dni;
    private String nombre;
    private boolean activo;
    
    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "codigo_colegio")
    private Colegio colegio;

    public Estudiante() {}
    public Estudiante(String dni, String nombre, boolean activo, Genero genero, LocalDate fechaIngreso) {
        this.dni = dni;
        this.nombre = nombre;
        this.activo = activo;
        this.genero = genero;
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", genero=" + genero + "]";
    }

    // Getters y Setters...
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(LocalDate fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public Colegio getColegio() { return colegio; }
    public void setColegio(Colegio colegio) { this.colegio = colegio; }
}

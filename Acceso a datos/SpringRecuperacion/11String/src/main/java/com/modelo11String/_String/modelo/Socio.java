package com.modelo11String._String.modelo;



import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "socio")
public class Socio {

    @Id // Clave manual String (DNI)
    private String dni;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    // Relación 1:1 inversa. El carnet se encarga de la FK.
    @OneToOne(mappedBy = "socio", cascade = CascadeType.ALL)
    private Carnet carnet;

    // Constructor Vacío (Obligatorio para Hibernate)
    public Socio() {}

    // Constructor Completo
    public Socio(String dni, String nombre, LocalDate fechaNacimiento, boolean activo, Genero genero) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.activo = activo;
        this.genero = genero;
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }
    public Carnet getCarnet() { return carnet; }
    public void setCarnet(Carnet carnet) { this.carnet = carnet; }

    @Override
    public String toString() {
        return "Socio [dni=" + dni + ", nombre=" + nombre + ", activo=" + activo + "]";
    }
}

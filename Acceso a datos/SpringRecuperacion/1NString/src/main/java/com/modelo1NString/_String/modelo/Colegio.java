package com.modelo1NString._String.modelo;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//Lado 1
@Entity
@Table(name = "colegio")
public class Colegio {
    @Id
    private String codigoCentro;
    private String nombre;
    private boolean publico;
    private LocalDate fechaFundacion;

    @OneToMany(mappedBy = "colegio", cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Colegio() {}
    public Colegio(String codigoCentro, String nombre, boolean publico, LocalDate fechaFundacion) {
        this.codigoCentro = codigoCentro;
        this.nombre = nombre;
        this.publico = publico;
        this.fechaFundacion = fechaFundacion;
    }

    @Override
    public String toString() {
        return "Colegio [id=" + codigoCentro + ", nombre=" + nombre + ", publico=" + publico + "]";
    }

    // Getters y Setters...
    public String getCodigoCentro() { return codigoCentro; }
    public void setCodigoCentro(String codigoCentro) { this.codigoCentro = codigoCentro; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isPublico() { return publico; }
    public void setPublico(boolean publico) { this.publico = publico; }
    public LocalDate getFechaFundacion() { return fechaFundacion; }
    public void setFechaFundacion(LocalDate fechaFundacion) { this.fechaFundacion = fechaFundacion; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public void setEstudiantes(List<Estudiante> estudiantes) { this.estudiantes = estudiantes; }
}

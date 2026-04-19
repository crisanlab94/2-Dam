package com.modeloNMString._String.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

//Lado inverso

@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    private String dni;
    private String nombre;
    private boolean becado;

    @ManyToMany(mappedBy = "estudiantes", fetch = FetchType.EAGER)
    private List<Curso> cursos = new ArrayList<>();

    // Constructores
    public Estudiante() {}
    public Estudiante(String dni, String nombre, boolean becado) {
        this.dni = dni;
        this.nombre = nombre;
        this.becado = becado;
    }

    // toString (Sin la lista para evitar bucle infinito)
    @Override
    public String toString() {
        return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", becado=" + becado + "]";
    }

    // Getters y Setters
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isBecado() { return becado; }
    public void setBecado(boolean becado) { this.becado = becado; }
    public List<Curso> getCursos() { return cursos; }
    public void setCursos(List<Curso> cursos) { this.cursos = cursos; }
}

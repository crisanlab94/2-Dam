package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private int id;
    private String titulo;
    private LocalDate fechaInicio;
    private List<Estudiante> listaAlumnos; // Relación N:M

    public Curso() {
        // Inicializamos la lista en el constructor para evitar NullPointerException
        this.listaAlumnos = new ArrayList<>();
    }

    public Curso(int id, String titulo, LocalDate fechaInicio) {
        this.id = id;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.listaAlumnos = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public List<Estudiante> getListaAlumnos() { return listaAlumnos; }
    public void setListaAlumnos(List<Estudiante> listaAlumnos) { this.listaAlumnos = listaAlumnos; }

    @Override
    public String toString() {
        return "Curso: " + titulo + " (Inicia: " + fechaInicio + ") - Alumnos matriculados: " + listaAlumnos.size();
    }
}
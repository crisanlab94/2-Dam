package com.modelo11Int._Int.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id // ID Manual
    private int id;
    private String nombreDepto;
    private double presupuesto;

    @OneToOne
    @JoinColumn(name = "id_director") // Dueño de la relación (FK)
    private Director director;

    public Departamento() {}

    public Departamento(int id, String nombreDepto, double presupuesto) {
        this.id = id;
        this.nombreDepto = nombreDepto;
        this.presupuesto = presupuesto;
    }

    @Override
    public String toString() {
        return "Departamento [id=" + id + ", nombre=" + nombreDepto + ", presupuesto=" + presupuesto + "]";
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombreDepto() { return nombreDepto; }
    public void setNombreDepto(String nombreDepto) { this.nombreDepto = nombreDepto; }
    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }
    public Director getDirector() { return director; }
    public void setDirector(Director director) { this.director = director; }
}

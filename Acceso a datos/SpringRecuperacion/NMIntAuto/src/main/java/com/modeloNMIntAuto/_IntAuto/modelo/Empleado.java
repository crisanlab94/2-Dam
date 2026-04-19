package com.modeloNMIntAuto._IntAuto.modelo;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

//Lado Inverso
@Entity
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private boolean activo;
    private double salario;

    @ManyToMany(mappedBy = "empleados", fetch = FetchType.EAGER) // Referencia al atributo en Proyecto
    private List<Proyecto> proyectos = new ArrayList<>();

    public Empleado() {}
    public Empleado(String nombre, boolean activo, double salario) {
        this.nombre = nombre;
        this.activo = activo;
        this.salario = salario;
    }

    // toString sin la lista de proyectos para evitar bucles infinitos
    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", activo=" + activo + "]";
    }

    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public List<Proyecto> getProyectos() { return proyectos; }
    public void setProyectos(List<Proyecto> proyectos) { this.proyectos = proyectos; }
}

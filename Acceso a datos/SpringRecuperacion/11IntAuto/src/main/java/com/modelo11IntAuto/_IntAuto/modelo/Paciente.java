package com.modelo11IntAuto._IntAuto.modelo;

import jakarta.persistence.*; 
import java.time.LocalDate;   
/**
 * La anotación @Entity indica que esta clase es una tabla en la base de datos.
 * El parámetro 'name' debe coincidir con el nombre de la tabla en tu SQL.
 */
@Entity(name = "paciente") 
public class Paciente {

    /**
     * @Id: Indica que este atributo es la clave primaria
     * @GeneratedValue: Configura el ID como autoincremental (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean asegurado;

    /**
     * @Enumerated: Es vital para los Enums. 
     * EnumType.STRING hace que en la base de datos se guarde el texto (ej: "FEMENINO") 
     * y no un número de índice.
     */
    @Enumerated(EnumType.STRING)
    private Genero genero;

    /**
     * Relación 1:1: El mappedBy siempre va en la clase que no tiene la clave foránea en la base de datos (la clase "fuerte").
     * RELACIÓN 1:1 (Lado Fuerte)
     * @OneToOne: Define la relación uno a uno.
     * mappedBy: Indica que el dueño de la relación es el campo 'paciente' que está 
     * en la clase Expediente.
     * cascade: Si borras el paciente, se borra su expediente automáticamente.
     */
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Expediente expediente;

    /**
     * CONSTRUCTOR VACÍO: Es obligatorio. Hibernate lo necesita para crear 
     * el objeto cuando recupera datos de la BD
     */
    public Paciente() {
    }

    /**
     * CONSTRUCTOR CON PARÁMETROS: Útil para crear objetos rápidamente en los tests.
     */
    public Paciente(Long id, String nombre, LocalDate fechaNacimiento, boolean asegurado, Genero genero) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.asegurado = asegurado;
        this.genero = genero;
    }
    
    public Paciente(String nombre, LocalDate fecha, boolean asegurado, Genero genero) {
        this.nombre = nombre;
        this.fechaNacimiento = fecha;
        this.asegurado = asegurado;
        this.genero = genero;
    }

    // --- BLOQUE DE GETTERS Y SETTERS (Imprescindibles sin Lombok) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isAsegurado() {
        return asegurado;
    }

    public void setAsegurado(boolean asegurado) {
        this.asegurado = asegurado;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    /**
     * El método toString es útil para ver los datos en la consola 
     * cuando hagas System.out.println(paciente) en tu test[cite: 273].
     */
    @Override
    public String toString() {
        return "Paciente [id=" + id + ", nombre=" + nombre + ", asegurado=" + asegurado + ", genero=" + genero + "]";
    }
}

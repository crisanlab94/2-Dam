package com.modelo1NInt._Int.modelo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Lado N

@Entity
@Table(name = "medico")
public class Medico {
    @Id
    private int id; // ID Manual
    private String nombre;
    private boolean activo;
    
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    
    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "id_hospital")
    private Hospital hospital;

    public Medico() {}
    public Medico(int id, String nombre, boolean activo, Especialidad esp, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
        this.especialidad = esp;
        this.fechaContratacion = fecha;
    }

    @Override
    public String toString() {
        return "Medico [id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + "]";
    }
    // Getters y Setters...
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
    
    
}

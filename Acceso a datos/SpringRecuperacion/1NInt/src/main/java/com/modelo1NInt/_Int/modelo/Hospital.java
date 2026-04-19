package com.modelo1NInt._Int.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


//Lado 1
@Entity
@Table(name = "hospital")
public class Hospital {
    @Id
    private int id; // ID Manual
    private String nombre;
    private boolean publico;
    private LocalDate fechaApertura;

    @OneToMany(mappedBy = "hospital")
    private List<Medico> medicos = new ArrayList<>();

    public Hospital() {}
    public Hospital(int id, String nombre, boolean publico, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.publico = publico;
        this.fechaApertura = fecha;
    }

    @Override
    public String toString() {
        return "Hospital [id=" + id + ", nombre=" + nombre + "]";
    }
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
	public boolean isPublico() {
		return publico;
	}
	public void setPublico(boolean publico) {
		this.publico = publico;
	}
	public LocalDate getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(LocalDate fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}
    
}

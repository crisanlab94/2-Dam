package com.modelo11IntAuto._IntAuto.modelo;


import jakarta.persistence.*; 
import java.time.LocalDateTime;

/**
 * La anotación @Entity define esta clase como una tabla en la BD.
 * 'name' debe coincidir con el nombre de la tabla en tu script SQL.
 */
@Entity(name = "expediente")
public class Expediente {

    /**
     * @Id: Clave primaria.
     * @GeneratedValue: Incremento automático gestionado por la base de datos (IDENTITY).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo String: Para textos largos como el historial médico.
     */
    private String historial;

    /**
     * Tipo double: Para valores numéricos con decimales (ej: medidas corporales).
     */
    private double peso;
    private double altura;

    /**
     * Tipo LocalDateTime: Para registrar el momento exacto (fecha y hora) 
     * en que se creó el expediente.
     */
    private LocalDateTime fechaApertura;

    /**
     * RELACIÓN 1:1 (Lado Débil/Hijo)
     * @OneToOne: Indica que un expediente pertenece a un solo paciente.
     * @JoinColumn: Crea la columna de unión física en la tabla (la FK).
     * 'name' es el nombre de la columna en la BD (paciente_id).
     * 'unique = true' es lo que garantiza a nivel de base de datos que la relación sea 1:1.
     */
    @OneToOne
    @JoinColumn(name = "paciente_id", unique = true, nullable = false)
    private Paciente paciente;

    /**
     * CONSTRUCTOR VACÍO: Obligatorio para que Hibernate funcione.
     */
    public Expediente() {
    }

    /**
     * CONSTRUCTOR CON PARÁMETROS: Para facilitar la creación en el método main del Test.
     */
    public Expediente(Long id, String historial, double peso, double altura, LocalDateTime fechaApertura, Paciente paciente) {
        this.id = id;
        this.historial = historial;
        this.peso = peso;
        this.altura = altura;
        this.fechaApertura = fechaApertura;
        this.paciente = paciente;
    }
    
    

    public Expediente(String historial, double peso, double altura, LocalDateTime fechaApertura) {
		super();
		
		this.historial = historial;
		this.peso = peso;
		this.altura = altura;
		this.fechaApertura = fechaApertura;
	}

	// --- BLOQUE DE GETTERS Y SETTERS (Obligatorios sin Lombok) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public LocalDateTime getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDateTime fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * toString: Muy útil para depurar y ver los datos en la consola del Test.
     */
    @Override
    public String toString() {
        return "Expediente [id=" + id + ", historial=" + historial + ", peso=" + peso + 
               ", altura=" + altura + ", fechaApertura=" + fechaApertura + "]";
    }
}

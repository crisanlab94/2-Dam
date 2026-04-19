package com.modelo11String._String.modelo;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "carnet")
public class Carnet {

    @Id // Clave manual String (Código de barras)
    private String codigoBarras;
    private LocalDateTime fechaEmision;
    private double cuotaMensual;

    @OneToOne
    @JoinColumn(name = "dni_socio") // Columna FK en la base de datos
    private Socio socio;

    public Carnet() {}

    public Carnet(String codigoBarras, LocalDateTime fechaEmision, double cuotaMensual) {
        this.codigoBarras = codigoBarras;
        this.fechaEmision = fechaEmision;
        this.cuotaMensual = cuotaMensual;
    }

    // Getters y Setters
    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }
    public double getCuotaMensual() { return cuotaMensual; }
    public void setCuotaMensual(double cuotaMensual) { this.cuotaMensual = cuotaMensual; }
    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }

    @Override
    public String toString() {
        return "Carnet [codigo=" + codigoBarras + ", cuota=" + cuotaMensual + "]";
    }
}

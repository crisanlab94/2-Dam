package modelo;


import java.time.LocalDate;

/**
 * Clase principal que representa un dispositivo inteligente.
 * Contiene una referencia a su objeto Detalle (Relación 1:1).
 */
public class Dispositivo {
    private String id;
    private String nombre;
    private Tipo tipo;
    private LocalDate fecha;
    private boolean activo;
    private Detalle detalle;

    // Constructores
    public Dispositivo() {}

    public Dispositivo(String id, String nombre, Tipo tipo, LocalDate fecha, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.activo = activo;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }

    // Método toString
    @Override
    public String toString() {
        return "Dispositivo [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + 
               ", fecha=" + fecha + ", activo=" + activo + ", detalle=" + detalle + "]";
    }
}
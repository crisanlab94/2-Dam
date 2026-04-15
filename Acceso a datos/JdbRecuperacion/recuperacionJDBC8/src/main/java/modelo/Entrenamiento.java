package modelo;

import java.sql.Date;

public class Entrenamiento {
    private int id;
    private Socio socio; // Referencia al objeto padre
    private Date fecha;
    private TipoActividad tipoActividad;
    private int duracionMin;

    public Entrenamiento() {}

    public Entrenamiento(int id, Socio socio, Date fecha, TipoActividad tipoActividad, int duracionMin) {
        this.id = id;
        this.socio = socio;
        this.fecha = fecha;
        this.tipoActividad = tipoActividad;
        this.duracionMin = duracionMin;
    }

    // Getters y Setters con un solo return
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public TipoActividad getTipoActividad() { return tipoActividad; }
    public void setTipoActividad(TipoActividad tipoActividad) { this.tipoActividad = tipoActividad; }
    public int getDuracionMin() { return duracionMin; }
    public void setDuracionMin(int duracionMin) { this.duracionMin = duracionMin; }

    @Override
    public String toString() {
        String res = "Entrenamiento: " + tipoActividad + " | Duración: " + duracionMin + " min";
        return res;
    }
}
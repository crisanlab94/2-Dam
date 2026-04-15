package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Mision {
    private int id;
    private Astronauta astronauta; // Relación (Objeto completo)
    private String nombreNave;
    private LocalDate fechaLanzamiento;     // Solo Año-Mes-Día
    private LocalDateTime ultimaConexion;   // Año-Mes-Día + Hora:Min:Seg
    private boolean combustibleExtra;       // Otro booleano
    private EstadoMision estado;            // Segundo Enum

    public Mision() {}

    public Mision(int id, Astronauta astronauta, String nombreNave, LocalDate fechaLanzamiento, 
                  LocalDateTime ultimaConexion, boolean combustibleExtra, EstadoMision estado) {
        this.id = id;
        this.astronauta = astronauta;
        this.nombreNave = nombreNave;
        this.fechaLanzamiento = fechaLanzamiento;
        this.ultimaConexion = ultimaConexion;
        this.combustibleExtra = combustibleExtra;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Astronauta getAstronauta() { return astronauta; }
    public void setAstronauta(Astronauta astronauta) { this.astronauta = astronauta; }

    public String getNombreNave() { return nombreNave; }
    public void setNombreNave(String nombreNave) { this.nombreNave = nombreNave; }

    public LocalDate getFechaLanzamiento() { return fechaLanzamiento; }
    public void setFechaLanzamiento(LocalDate fechaLanzamiento) { this.fechaLanzamiento = fechaLanzamiento; }

    public LocalDateTime getUltimaConexion() { return ultimaConexion; }
    public void setUltimaConexion(LocalDateTime ultimaConexion) { this.ultimaConexion = ultimaConexion; }

    public boolean isCombustibleExtra() { return combustibleExtra; }
    public void setCombustibleExtra(boolean combustibleExtra) { this.combustibleExtra = combustibleExtra; }

    public EstadoMision getEstado() { return estado; }
    public void setEstado(EstadoMision estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Mision #" + id + " [" + nombreNave + "] - Piloto: " + astronauta.getNombre() + 
               " | Lanzamiento: " + fechaLanzamiento + " | Estado: " + estado;
    }
}
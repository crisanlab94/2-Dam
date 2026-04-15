package modelo;

public class Astronauta {
    private int id;
    private String nombre;
    private RangoAstronauta rango;
    private int horasVuelo;
    private boolean activo; // Tipo boolean para el TINYINT(1) de SQL

    public Astronauta() {}

    public Astronauta(int id, String nombre, RangoAstronauta rango, int horasVuelo, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.rango = rango;
        this.horasVuelo = horasVuelo;
        this.activo = activo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public RangoAstronauta getRango() { return rango; }
    public void setRango(RangoAstronauta rango) { this.rango = rango; }

    public int getHorasVuelo() { return horasVuelo; }
    public void setHorasVuelo(int horasVuelo) { this.horasVuelo = horasVuelo; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "Astronauta [ID=" + id + ", Nombre=" + nombre + ", Rango=" + rango + 
               ", Horas=" + horasVuelo + ", Activo=" + activo + "]";
    }
}
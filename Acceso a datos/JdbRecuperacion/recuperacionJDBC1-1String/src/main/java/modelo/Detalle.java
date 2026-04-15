package modelo;



/**
 * Clase que representa la información técnica adicional de un dispositivo.
 * Relación 1:1 con Dispositivo.
 */
public class Detalle {
    private double consumo;
    private String version;

    // Constructores
    public Detalle() {}

    public Detalle(double consumo, String version) {
        this.consumo = consumo;
        this.version = version;
    }

    // Getters y Setters
    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    // Método toString
    @Override
    public String toString() {
        return "Detalle [consumo=" + consumo + " W/h, version=" + version + "]";
    }
}
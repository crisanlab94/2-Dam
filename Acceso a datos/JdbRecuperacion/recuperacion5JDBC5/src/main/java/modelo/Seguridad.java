package modelo;

public class Seguridad {
    private int pin;
    private String nivel;

    // Constructor vacío (necesario para frameworks y limpieza)
    public Seguridad() {}

    // Constructor completo
    public Seguridad(int pin, String nivel) {
        this.pin = pin;
        this.nivel = nivel;
    }

    // Getters y Setters
    public int getPin() { return pin; }
    public void setPin(int pin) { this.pin = pin; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    @Override
    public String toString() {
        return "[PIN=" + pin + ", Nivel=" + nivel + "]";
    }
}

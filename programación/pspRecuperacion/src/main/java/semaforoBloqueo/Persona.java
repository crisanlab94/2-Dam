package semaforoBloqueo;

public class Persona implements Runnable {
    private String nombre;
    private Cajero cajero;
    public Persona(String nombre, Cajero cajero) {
        this.nombre = nombre;
        this.cajero = cajero;
    }
    @Override
    public void run() { cajero.usar(nombre); }
} 



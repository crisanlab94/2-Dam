package modelo;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private int id;
    private String nombre;
    private String email;
    private TipoCuota tipoCuota;
    private boolean estaActivo;
    private List<Entrenamiento> entrenamientos; // Relación 1:N

    public Socio() {
        this.entrenamientos = new ArrayList<Entrenamiento>();
    }

    public Socio(int id, String nombre, String email, TipoCuota tipoCuota, boolean estaActivo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tipoCuota = tipoCuota;
        this.estaActivo = estaActivo;
        this.entrenamientos = new ArrayList<Entrenamiento>();
    }

    // Getters y Setters con un solo return
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public TipoCuota getTipoCuota() { return tipoCuota; }
    public void setTipoCuota(TipoCuota tipoCuota) { this.tipoCuota = tipoCuota; }
    public boolean isEstaActivo() { return estaActivo; }
    public void setEstaActivo(boolean estaActivo) { this.estaActivo = estaActivo; }
    public List<Entrenamiento> getEntrenamientos() { return entrenamientos; }
    public void setEntrenamientos(List<Entrenamiento> entrenamientos) { this.entrenamientos = entrenamientos; }

    @Override
    public String toString() {
        String res = "Socio: " + nombre + " (" + tipoCuota + ")";
        return res;
    }
}
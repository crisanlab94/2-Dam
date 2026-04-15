package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String id;
    private String nombre;
    private String email;
    private TipoPlan planActivo;
    private int puntosFidelidad;
    private boolean notificacionesPush; // Añadido
    private String dispositivoPrincipal; // Añadido
    
    // La lista para la hidratación (Relación 1:N)
    private List<Reproduccion> historial; 

    public Usuario() {
        this.historial = new ArrayList<Reproduccion>();
    }

    public Usuario(String id, String nombre, String email, TipoPlan planActivo, 
                   int puntosFidelidad, boolean notificacionesPush, String dispositivoPrincipal) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.planActivo = planActivo;
        this.puntosFidelidad = puntosFidelidad;
        this.notificacionesPush = notificacionesPush;
        this.dispositivoPrincipal = dispositivoPrincipal;
        this.historial = new ArrayList<Reproduccion>();
    }

    // --- GETTERS Y SETTERS (Un solo return por método) ---

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoPlan getPlanActivo() {
        return planActivo;
    }

    public void setPlanActivo(TipoPlan planActivo) {
        this.planActivo = planActivo;
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(int puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    public boolean isNotificacionesPush() {
        return notificacionesPush;
    }

    public void setNotificacionesPush(boolean notificacionesPush) {
        this.notificacionesPush = notificacionesPush;
    }

    public String getDispositivoPrincipal() {
        return dispositivoPrincipal;
    }

    public void setDispositivoPrincipal(String dispositivoPrincipal) {
        this.dispositivoPrincipal = dispositivoPrincipal;
    }

    public List<Reproduccion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Reproduccion> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        String info = "Usuario: " + nombre + " (ID: " + id + ") | Plan: " + planActivo + 
                      " | Puntos: " + puntosFidelidad + " | Dispositivo: " + dispositivoPrincipal +
                      " | Notificaciones: " + (notificacionesPush ? "SÍ" : "NO");
        return info;
    }
}

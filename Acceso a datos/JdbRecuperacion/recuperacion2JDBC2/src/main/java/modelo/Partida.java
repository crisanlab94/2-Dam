package modelo;



import java.time.LocalDateTime;

public class Partida {
    private int id;
    private Jugador jugadorBlancas; // Mapeo de la clave foránea a objeto 
    private LocalDateTime fecha;
    private ResultadoAjedrez resultado; // Tipo enumerado 

    // Constructor por defecto
    public Partida() {}

    // Constructor completo
    public Partida(int id, Jugador jugadorBlancas, LocalDateTime fecha, ResultadoAjedrez resultado) {
        this.id = id;
        this.jugadorBlancas = jugadorBlancas;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Jugador getJugadorBlancas() { return jugadorBlancas; }
    public void setJugadorBlancas(Jugador jugadorBlancas) { this.jugadorBlancas = jugadorBlancas; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public ResultadoAjedrez getResultado() { return resultado; }
    public void setResultado(ResultadoAjedrez resultado) { this.resultado = resultado; }

    @Override
    public String toString() {
        return "PARTIDA [" + id + "] - Blancas: " + jugadorBlancas.getNombre() + 
               " | Fecha: " + fecha + " | Resultado: " + resultado;
    }
}
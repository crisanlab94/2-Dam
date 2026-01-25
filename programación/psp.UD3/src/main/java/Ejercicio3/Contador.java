package Ejercicio3;

public class Contador {
    private int totalConexiones = 0;

    // Usamos synchronized para que no haya errores si muchos conectan a la vez
    public synchronized int incrementar() {
        totalConexiones++;
        return totalConexiones;
    }

    public synchronized int getValor() {
        return totalConexiones;
    }
}
package semaforoBloqueo;

import java.util.concurrent.Semaphore;

public class Cajero {
    private Semaphore sem;
    private int operaciones = 0;

    public Cajero(Semaphore sem) { this.sem = sem; }

    public void usar(String nombre) {
        try {
            sem.acquire(); // Se queda esperando si hay alguien
            System.out.println(nombre + " sacando dinero...");
            Thread.sleep(1000);
            contar();
        } catch (InterruptedException e) { e.printStackTrace(); } 
        finally { sem.release(); }
    }
    private synchronized void contar() { operaciones++; }
}
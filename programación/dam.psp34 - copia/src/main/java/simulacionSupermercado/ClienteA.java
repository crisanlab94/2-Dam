package simulacionSupermercado;

import java.util.concurrent.Semaphore;

public class ClienteA  implements Runnable {
    private String nombre;
    private Semaphore hayClientes;
    private Semaphore hayTurno;

    public ClienteA(String nombre, Semaphore hayClientes, Semaphore hayTurno) {
        this.nombre = nombre;
        this.hayClientes = hayClientes;
        this.hayTurno = hayTurno;
    }

    @Override
    public void run() {
        try {
        	hayClientes.release(); // avisar al cajero
        	 System.out.println(nombre + " está en la cola");
            hayTurno.acquire(); // esperar turno
       
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



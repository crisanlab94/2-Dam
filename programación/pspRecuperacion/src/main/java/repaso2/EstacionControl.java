package repaso2;

import java.util.concurrent.Semaphore;



public class EstacionControl {
	private TipoTransmision tipo;
	private int anchoBanda;
	private int cuentaActual;
	
	public EstacionControl(TipoTransmision tipo, int anchoBanda) {
		super();
		this.tipo = tipo;
		this.anchoBanda = anchoBanda;
		this.cuentaActual = 0;
	}
	
	// El método es 'synchronized' para que solo un hilo a la vez compruebe la cuenta
    public synchronized boolean intentarEntrar(Sonda  sonda, Semaphore sem) {
        boolean exito = false;
    	if (cuentaActual < anchoBanda) {
            // Caso A: Hay sitio
            cuentaActual++; // Ocupamos una plaza
            System.out.println("La sonda con id " + sonda.getId() + " ha podido entrar en " + tipo);
            
            // Como ya hemos comprobado que hay sitio, el hilo puede hacer acquire()
            // sin miedo a quedarse bloqueado eternamente.
            try {
                sem.acquire();
                exito = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            

        } else {
            //Está lleno
            System.out.println("La sonda con id " + sonda.getId() + " no ha podido entrar en " + tipo);
        }
    	return exito;
    }

    // Método para liberar la plaza cuando el desarrollador termine
    public synchronized void salir(Semaphore sem) {
        cuentaActual--; 
        sem.release();
    }
	
	
}

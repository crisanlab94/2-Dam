package CloudDev;

import java.util.concurrent.Semaphore;

public class EntornoCloud  {
	private Tipo tipo;
	private int aforo;
	private int cuentaActual;
	
	
	
	
	public EntornoCloud(Tipo tipo, int aforo) {
		super();
		this.tipo = tipo;
		this.aforo = aforo;
		this.cuentaActual = 0;
	}


	
	// El método es 'synchronized' para que solo un hilo a la vez compruebe la cuenta
    public synchronized boolean intentarIniciarSesion(Desarrollador ds, Semaphore sem) {
    	boolean exito = false;
    	if (cuentaActual < aforo) {
            // Caso A: Hay sitio
            cuentaActual++; // Ocupamos una plaza
            System.out.println("El desarrollador con id " + ds.getId() + " ha podido iniciar sesión en " + tipo);
            
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
            System.out.println("El desarrollador con id " + ds.getId() + " NO ha podido iniciar sesión en " + tipo);
        }
    	return exito;
    }

    // Método para liberar la plaza cuando el desarrollador termine
    public synchronized void salir(Semaphore sem) {
        cuentaActual--; 
        sem.release();
    }
	

/*
	// Esta version si usamos el tryacquire
	public void iniciarSesion(Desarrollador ds, Semaphore sem) {
		try {
			sem.acquire();
			System.out.println("El desarrollador con id"+ ds.getId()+ "ha podido iniciar sesión en " + tipo );
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//Libera plaza para que entre otro
			sem.release();
			
		}
		*/
	}

	
	
	
	

	

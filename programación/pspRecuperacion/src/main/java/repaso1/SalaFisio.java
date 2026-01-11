package repaso1;

import java.util.concurrent.Semaphore;

//Usa boolean siempre que el enunciado diga: "Si no puede entrar, que de un mensaje de error y no se quede esperando".
//No uses boolean (usa void) si el enunciado dice: "Los hilos deben esperar su turno hasta que haya una plaza libre"..

public class SalaFisio {
	private TipoTratamiento tipoTratamiento;
	private int aforo;
	private int cuentaActual;
	
	public SalaFisio(TipoTratamiento tipoTratamiento, int aforo) {
		super();
		this.tipoTratamiento = tipoTratamiento;
		this.aforo = aforo;
		this.cuentaActual = 0;
	}
	
	  public synchronized boolean intentarEntrar(Atleta atleta, Semaphore sem) {
		  boolean puedeEntrar = false;  
		  if (cuentaActual < aforo) {
	          
	            cuentaActual++; // Ocupamos una plaza
	            System.out.println("El atleta con id " + atleta.getId() + " ha podido entrar en su sala " + tipoTratamiento);
	            
	            // Como ya hemos comprobado que hay sitio, el hilo puede hacer acquire()
	            // sin miedo a quedarse bloqueado eternamente.
	            try {
	                sem.acquire();
	                puedeEntrar = true;
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            

	        } else {
	            //Está lleno
	            System.out.println("El altela con id " + atleta.getId() + " nO ha podido entrar en su sale  " + tipoTratamiento);
	        }
		  return puedeEntrar;
	    }

	    // Método para liberar la plaza cuando el atleta termine
	    public synchronized void salir(Semaphore sem) {
	        cuentaActual--; 
	        sem.release();
	    }

		public TipoTratamiento getTipoTratamiento() {
			return tipoTratamiento;
		}

		public void setTipoTratamiento(TipoTratamiento tipoTratamiento) {
			this.tipoTratamiento = tipoTratamiento;
		}

		public int getAforo() {
			return aforo;
		}

		public void setAforo(int aforo) {
			this.aforo = aforo;
		}

		public int getCuentaActual() {
			return cuentaActual;
		}

		public void setCuentaActual(int cuentaActual) {
			this.cuentaActual = cuentaActual;
		}
	    
	    
	    

}

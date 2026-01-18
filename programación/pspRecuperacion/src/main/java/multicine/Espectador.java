package multicine;



public class Espectador implements Runnable{
	 private String dni;
	    private String codigoQR;
	    private TipoCine tipo; 
	    private Cine cine;
	    
		public Espectador(String dni, String codigoQR, TipoCine tipo, Cine cine) {
			super();
			this.dni = dni;
			this.codigoQR = codigoQR;
			this.tipo = tipo;
			this.cine = cine;
		}
	    
		@Override
		public void run() {
		   
		    if (cine.validarAcceso(dni, codigoQR)) {
		        try {
		            if (tipo == TipoCine.SOLOCINE) { 
		                taquilla();
		            } else {
		                taquilla();
		                bar();
		            }
		            cine.registrarExito();
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    } else {
		        cine.registrarFallo();
		        System.err.println("[SEGURIDAD] " + dni + " bloqueado.");
		    }
		}
		  
		  private void taquilla() throws InterruptedException {
		        cine.getSemTaquilla().acquire(); 
		        System.out.println("[TAQUILLA] -> " + dni + " entrando en sala.");
		        Thread.sleep(2000);
		        cine.getSemTaquilla().release();
		  
		    }

		    private void bar() throws InterruptedException {
		    	cine.getSemBar().acquire(); 
		        System.out.println("[BAR] -> " + dni + " comprando palomitas...");
		        Thread.sleep(2000); // El trabajo va ANTES de soltar
		        cine.getSemBar().release(); // CORRECCIÓN: El release va al final
		        System.out.println("[BAR] <- " + dni + " ha terminado.");
		    }
	    

}

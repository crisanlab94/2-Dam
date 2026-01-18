package multicine;

import java.util.concurrent.Semaphore;

public class Cine {
	 private Semaphore semTaquilla;
	    private Semaphore semBar;
	    private int completarAcceso = 0;
	    private int fallosSeguridad = 0;
	    
	    public Cine(Semaphore semTaquilla, Semaphore semBar) {
	        this.semTaquilla = semTaquilla;
	        this.semBar = semBar;
	    }
	    
	    public boolean validarAcceso(String dni, String codigoQR) {
	        return dni.equals(codigoQR);
	    }
	    
	    public synchronized void registrarExito() { completarAcceso++; }
	    public synchronized void registrarFallo() { fallosSeguridad++; }

		public Semaphore getSemTaquilla() {
			return semTaquilla;
		}

		public void setSemTaquilla(Semaphore semTaquilla) {
			this.semTaquilla = semTaquilla;
		}

		public Semaphore getSemBar() {
			return semBar;
		}

		public void setSemBar(Semaphore semBar) {
			this.semBar = semBar;
		}

		 public void mostrarResumen() {
		        System.out.println("\n--- ESTADÍSTICAS CINE ---");
		        System.out.println("Espectadores que han completad su proceso: " + completarAcceso);
		        System.out.println("Espectadores con QR falso: " + fallosSeguridad);
		    }

	    
	    
}


	package simulacroLavadoCoches;

	import java.util.concurrent.Semaphore;

	public class LavadoraA implements Runnable {
	    private String nombre;
	    private Semaphore hayCoches;
	    private Semaphore hayTurno;
	    private int numCoches;

	    public LavadoraA(String nombre, Semaphore hayCoches, Semaphore hayTurno, int numCoches) {
	        this.nombre = nombre;
	        this.hayCoches = hayCoches;
	        this.hayTurno = hayTurno;
	        this.numCoches = numCoches;
	    }

	    @Override
	    public void run() {
	        for (int i = 0; i < numCoches; i++) {
	            try {
	                hayCoches.acquire(); // esperar coche
	                System.out.println(nombre + " lava coche " + (i+1));
	                Thread.sleep(6000); // tarda 6s en lavar
	                hayTurno.release(); // liberar turno
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	


}

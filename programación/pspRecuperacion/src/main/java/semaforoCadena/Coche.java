package semaforoCadena;


	public class Coche implements Runnable {
	    private String id;
	    private Lavadero lav;

	    public Coche(String id, Lavadero lav) { this.id = id; this.lav = lav; }

	    @Override
	    public void run() {
	        try {
	            // ETAPA 1
	            lav.getSemAspirado().acquire();
	            System.out.println(id + " aspirando...");
	            Thread.sleep(1000);
	            lav.getSemAspirado().release(); // LIBERA antes de la siguiente

	            // ETAPA 2
	            lav.getSemLavado().acquire();
	            System.out.println(id + " lavando...");
	            Thread.sleep(1000);
	            lav.getSemLavado().release();

	        } catch (InterruptedException e) {}
	    }
	}



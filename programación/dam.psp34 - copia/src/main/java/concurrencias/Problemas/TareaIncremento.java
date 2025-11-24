package concurrencias.Problemas;

public class TareaIncremento implements Runnable {
	  private EjemploContador contador;
	   public TareaIncremento(EjemploContador contador) {
	       this.contador = contador;
	   }
	   @Override
	   public void run() {
	       for (int i = 0; i < 1000; i++) {
	           contador.incrementar();
	           try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       }
	   }


}

package concurrencias.Problemas;

public class EjemploContador {
	//La zona critica es dentro de lo que compatimos
	
	  private int valor = 0;
	  
	  //Para sincronizar, entran de uno en uno
	  
	   public synchronized void  incrementar() { 
	       valor++;
	   }
	   public int getValor() {
	       return valor;
	   }


}

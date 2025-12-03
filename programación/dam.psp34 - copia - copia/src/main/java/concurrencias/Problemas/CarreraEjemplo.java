package concurrencias.Problemas;

public class CarreraEjemplo {
	public static void main(String[] args) throws InterruptedException {
		//Esto para ver lo que tarda
		long t_inicio= System.currentTimeMillis();
		
	       EjemploContador contador = new EjemploContador();
	       Thread t1 = new Thread(new TareaIncremento(contador));
	       Thread t2 = new Thread(new TareaIncremento(contador));
	       Thread t3 = new Thread(new TareaIncremento(contador));
	       Thread t4 = new Thread(new TareaIncremento(contador));
	       Thread t5 = new Thread(new TareaIncremento(contador));
	       Thread t6 = new Thread(new TareaIncremento(contador));
	       t1.start();
	       t2.start();
	       t3.start();
	       t4.start();
	       t5.start();
	       t6.start();
	       t1.join();
	       t2.join();
	       t3.join();
	       t4.join();
	       t5.join();
	       t6.join();
	       System.out.println("Valor final: " + contador.getValor());
	       
	       //Esto para ver lo que tarda
	       long t_fin= System.currentTimeMillis();
	       long tiempoPadre = t_fin - t_inicio;
	       
	       System.out.println("Tiempo total del hilo padre: "+ tiempoPadre + "mseg");
	   }


}

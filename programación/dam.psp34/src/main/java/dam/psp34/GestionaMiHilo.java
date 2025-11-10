package dam.psp34;

public class GestionaMiHilo {
	public static void main(String[] args) {
		/*MiHilo hilo1 = new MiHilo("Hilo1");
		System.out.println(hilo1.getState());
		
		hilo1.start();
		System.out.println(hilo1.getState());
		
       MiHilo hilo2 = new MiHilo("Hilo2");
		
		hilo2.start(); */
		
		MiHilo2 runnable1 = new MiHilo2("Hilo1");
		MiHilo2 runnable2 = new MiHilo2("Hilo2");
		
		Thread hilo1 = new Thread(runnable1);
		Thread hilo2 = new Thread(runnable2);
		
		hilo1.start();
		hilo2.start();
		
		System.out.println("En el main");
	
    }
}

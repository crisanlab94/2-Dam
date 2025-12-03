package boletin4.sincro;

public class GestionaCuenta {
	public static void main(String[] args) {
		
		Cuenta cuenta1= new Cuenta(((int)(Math.random()*500+1)));
		HiloIngresa hiloIngresa= new HiloIngresa(cuenta1);
		HiloRetira hiloRetira= new HiloRetira(cuenta1);
		HiloIngresa hiloIngresa1= new HiloIngresa(cuenta1);
		HiloRetira hiloRetira1= new HiloRetira(cuenta1);
		
		try {
		
			hiloIngresa.start(); //Primero star
			hiloRetira.start();
			hiloIngresa1.start(); 
			hiloRetira1.start();
			hiloIngresa.join();//Luego join
			hiloRetira.join();
			hiloIngresa1.join();
			hiloRetira1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	System.out.println("Padre:"+cuenta1.getSaldo());
	}

}

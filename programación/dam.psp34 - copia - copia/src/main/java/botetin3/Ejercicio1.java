package botetin3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ejercicio1 {
	public static void main(String[] args) {
	int tamTabla= 10000;
	int numHilos = 10;
	long t_comienzo = System.currentTimeMillis();
	
	Ejercicio1 e = new Ejercicio1();
	
	int[] tabla = e.construyeTablaNotas(tamTabla);
	List<Thread> hilos  = new ArrayList<Thread>();
	
	List<CalculaMaxHilo> objetosHilos  = new ArrayList<CalculaMaxHilo>();
	try {
		int rango = tamTabla / numHilos;
		for(int i = 0; i< numHilos; i++)
		{
			CalculaMaxHilo c = new CalculaMaxHilo();
			c .setTabla(tabla);
			c.setInicio(i*rango);
			c.setFin(c.getInicio()+rango-1);
			objetosHilos.add(c);
			Thread hilo = new Thread(c);
			hilos.add(hilo);
		}
		for(Thread h : hilos)
		{
				h.start();			
		}
		
		for(Thread h : hilos)
		{
				h.join();			
		}

		for ()
		
		long t_fin = System.currentTimeMillis();
  		long tiempototal = t_fin-t_comienzo;
 		 System.out.println("El proceso total ha tardado= "+ tiempototal +"mseg");
	
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		}
	
	int calculaMaximoTotal ( int [] tabla) {
		return tabla;
		
	}

	int[] construyeTablaNotas(int tamanyo)
	{
		int [] tabla1 =  new int [tamanyo];
		Random r = new Random();
		for (int i = 0; i < tabla1.length; i++) {
			try {
				int aleatorio = r.nextInt(500)+1;
				tabla1[i] = aleatorio;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tabla1;	
	}
	
	}


	public class CalculaMaxHilo implements Runnable{
		
		private int[] tabla;
		private int inicio;
		private int fin;
		private int maximoTramo;
		
		private int calculaMax( ) {
			int maxTotal = tabla[inicio];
			for (int i = inicio; i < fin; i++) {
				if (tabla[i] > maxTotal) {
					maxTotal = tabla[i];
				}
			}
			System.out.println("Maximo del rango "+ inicio +" - "+fin+":"+maxTotal);
			return maxTotal;
		}

		@Override
		public void run() {
			maximoTramo = calculaMax();
			
		}

		public int[] getTabla() {
			return tabla;
		}

		public void setTabla(int[] tabla) {
			this.tabla = tabla;
		}

		public int getInicio() {
			return inicio;
		}

		public void setInicio(int inicio) {
			this.inicio = inicio;
		}

		public int getFin() {
			return fin;
		}

		public void setFin(int fin) {
			this.fin = fin;
		}

		public int getMaximoTramo() {
			return maximoTramo;
		}

		public void setMaximoTramo(int maximoTramo) {
			this.maximoTramo = maximoTramo;
		}


		
	}
	







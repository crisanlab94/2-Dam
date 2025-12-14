
	package Boletin;

	import java.util.ArrayList;
	import java.util.List;

	public class GestionaEjercicio3 {
	    public static void main(String[] args) {
	        Thread hilo1 = new Thread(new Ejercicio2A());
	        Thread hilo2 = new Thread(new Ejercicio2B());

	        List<Thread.State> estadosHilo1 = new ArrayList<>();
	        List<Thread.State> estadosHilo2 = new ArrayList<>();

	        // Estado inicial
	        estadosHilo1.add(hilo1.getState());
	        estadosHilo2.add(hilo2.getState());

	        hilo1.start();
	        hilo2.start();

	        // Vamos consultando estados durante unos segundos
	        for (int i = 0; i < 10; i++) {
	            estadosHilo1.add(hilo1.getState());
	            estadosHilo2.add(hilo2.getState());

	            try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	        // Mostramos los estados almacenados
	        System.out.println("Estados de hilo1: " + estadosHilo1);
	        System.out.println("Estados de hilo2: " + estadosHilo2);
	    }
	}




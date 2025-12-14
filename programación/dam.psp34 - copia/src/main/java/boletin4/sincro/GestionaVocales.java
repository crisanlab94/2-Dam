package boletin4.sincro;

public class GestionaVocales {
	    public static void main(String[] args) {
	        String texto = "Este es un ejemplo de texto para contar las vocales con hilos en Java.";
	        ContadorVocales contador = new ContadorVocales();

	        // Creamos los 5 hilos, uno por cada vocal
	        Thread hiloA = new HiloVocales('a', texto, contador);
	        Thread hiloE = new HiloVocales('e', texto, contador);
	        Thread hiloI = new HiloVocales('i', texto, contador);
	        Thread hiloO = new HiloVocales('o', texto, contador);
	        Thread hiloU = new HiloVocales('u', texto, contador);

	        // Lanzamos los hilos
	        hiloA.start();
	        hiloE.start();
	        hiloI.start();
	        hiloO.start();
	        hiloU.start();

	        // Esperamos a que terminen todos
	        try {
	            hiloA.join();
	            hiloE.join();
	            hiloI.join();
	            hiloO.join();
	            hiloU.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

	        // Resultado final
	        System.out.println("Número total de vocales en el texto: " + contador.getVocalesTotales());
	    }
	}
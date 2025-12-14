package boletin4.sincro;

public class HiloVocales extends Thread{
	 private char vocal;
	    private String texto;
	    private ContadorVocales contador;

	    public HiloVocales(char vocal, String texto, ContadorVocales contador) {
	        this.vocal = vocal;
	        this.texto = texto;
	        this.contador = contador;
	    }

	    @Override
	    public void run() {
	        int cuenta = 0;
	        for (int i = 0; i < texto.length(); i++) {
	            if (Character.toLowerCase(texto.charAt(i)) == vocal) {
	                cuenta++;
	            }
	        }
	        System.out.println("Hilo " + vocal + " encontró " + cuenta + " veces la vocal '" + vocal + "'");
	        contador.sumar(cuenta);
	    }
	}
	
	
	



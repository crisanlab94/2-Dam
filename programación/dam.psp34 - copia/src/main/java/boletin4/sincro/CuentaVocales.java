
	package boletin4.sincro;

	class ContadorVocales {
	    private int vocalesTotales = 0;

	    // Método sincronizado para evitar condiciones de carrera
	    public synchronized void sumar(int cantidad) {
	        vocalesTotales += cantidad;
	    }

	    public int getVocalesTotales() {
	        return vocalesTotales;
	    }
	}
	
	
	
	
	
	
	
	



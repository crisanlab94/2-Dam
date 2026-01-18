package Monitor;

public class GestionaAlmacen {
	
	    public static void main(String[] args) {
	        Almacen a = new Almacen();
	        // Lanzamos unos para meter y otros para sacar
	        for(int i=0; i<5; i++) {
	            new Thread(new Repartidor(a)).start();
	            new Thread(new Repartidor(a)).start();
	        }
	    }
	}



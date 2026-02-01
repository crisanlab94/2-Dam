package MapaLogin;

public class Lanzador {
	public static void main(String[] args) {
	    // Pepe falla 3 veces
	    new Thread(new ClienteLogin("Pepe", "error")).start();
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    new Thread(new ClienteLogin("Pepe", "error")).start();
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    new Thread(new ClienteLogin("Pepe", "error")).start();
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // Al 4º intento, aunque ponga la clave bien ("1234"), el monitor dirá que está bloqueado
	    new Thread(new ClienteLogin("Pepe", "1234")).start();
	}

}

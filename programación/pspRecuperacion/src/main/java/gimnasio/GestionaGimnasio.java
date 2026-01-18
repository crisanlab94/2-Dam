package gimnasio;

public class GestionaGimnasio {
	
	    public static void main(String[] args) {
	        // 1. Creamos el gimnasio con un aforo de 5 personas
	        Gimnasio miGym = new Gimnasio(5);

	        // 2. Lanzamos, por ejemplo, 15 clientes de golpe
	        // Como el aforo es 5, 10 clientes deberían ser RECHAZADOS
	        for (int i = 1; i <= 15; i++) {
	            Cliente c = new Cliente("Cliente-" + i, miGym);
	            
	            
	            Thread hilo = new Thread(c);
	            hilo.start();
	        }
	    }
	}



package amazon;

public class GestionaAmazon {
	    public static void main(String[] args)  {
	        Centro zonaA = new Centro(TipoVehiculo.CAMION, 3);
	        Centro zonaB = new Centro(TipoVehiculo.FURGONETA, 5);
	        
	        Thread[] hilos = new Thread[18]; // 8 camiones + 10 furgos

	        // Lanzar Camiones
	        for(int i=1; i<=8; i++) {
	            String pass = (i==5) ? "ERROR" : "C-"+i;
	            hilos[i-1] = new Thread(new Vehiculo("C-"+i, pass, zonaA, TipoVehiculo.CAMION));
	            hilos[i-1].start();
	        }

	        // Lanzar Furgonetas
	        for(int i=1; i<=10; i++) {
	            String pass = (i==2) ? "ERROR" : "F-"+i;
	            hilos[8+i-1] = new Thread(new Vehiculo("F-"+i, pass, zonaB, TipoVehiculo.FURGONETA));
	            hilos[8+i-1].start();
	        }

	        for(Thread t : hilos)
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        zonaA.mostrarResumen();
	        zonaB.mostrarResumen();
	    }
	}



package controlPeaje;


public class GestionaAutopista {
	public static void main(String[] args)  {
        Autopista autopista = new Autopista(4);
      
        Thread[] hilos = new Thread[20];
       
        for (int i = 1; i <= 20; i++) {
            String idVehiculo = "V-" + i;
            String passVehiculo;

            // COMPROBACIÓN DE SEGURIDAD (Vehículos 7 y 13)
            if (i == 7 || i == 13) {
                passVehiculo = "CONTRASEÑA_ERRONEA"; // Esto causará el fraude
            } else {
                passVehiculo = idVehiculo; // La contraseña correcta (ID = PASS)
            }

            // Creamos el vehículo con las credenciales decididas arriba
            Vehiculo v = new Vehiculo(idVehiculo, passVehiculo, autopista);
            
            hilos[i-1] = new Thread(v);
            hilos[i-1].start();
        }


        // 4. ESPERAR A TODOS (
        for (int i = 0; i < hilos.length; i++) {
            if (hilos[i] != null) {
                try {
					hilos[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }

        // 5. Mostrar los resúmenes finales de cada uno
        System.out.println("\n=== RESULTADOS FINALES DE LA JORNADA ===");
        autopista.mostrarResumen();
        
    }

}

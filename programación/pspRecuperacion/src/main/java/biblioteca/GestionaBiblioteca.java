package biblioteca;

public class GestionaBiblioteca {
	public static void main(String[] args)  {
        SalaEstudio salaEstudio = new SalaEstudio(6);
      
        Thread[] hilos = new Thread[15];
        int contadorHilos = 0;

        for (int i = 1; i <= 15; i++) {
            // Pasamos: ID, Login, Password y el ENTORNO DE
            Estudiante e = new Estudiante("E"+i,"E"+i,"Nombre"+ i,salaEstudio);
            hilos[contadorHilos] = new Thread(e);
            hilos[contadorHilos].start();
            contadorHilos++;
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
        salaEstudio.mostrarResumen();
        
    }
}



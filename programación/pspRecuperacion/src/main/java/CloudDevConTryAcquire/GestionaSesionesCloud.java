package CloudDevConTryAcquire;

public class GestionaSesionesCloud {
    public static void main(String[] args)  {
        // 1. Creamos los dos entornos con sus aforos (esto crea los semáforos internos)
        EntornoCloud entornoDE = new EntornoCloud(Tipo.ED, 20);
        EntornoCloud entornoEF = new EntornoCloud(Tipo.EP, 8);

        // Creamos un array para guardar TODOS los hilos y poder hacerles el join luego
        // 35 (DE) + 15 (EF) = 50 hilos en total
        Thread[] hilos = new Thread[50];
        int contadorHilos = 0;

        // 2. Lanzamos los 35 hilos para el entorno DE (Aforo 20)
        for (int i = 1; i <= 35; i++) {
            // Pasamos: ID, Login, Password y el ENTORNO DE
            Desarrollador d = new Desarrollador("D-" + i, "userD" + i, "userD" + i, entornoDE);
            hilos[contadorHilos] = new Thread(d);
            hilos[contadorHilos].start();
            contadorHilos++;
        }

        // 3. Lanzamos los 15 hilos para el entorno EF (Aforo 8)
        for (int i = 1; i <= 15; i++) {
            // Pasamos: ID, Login, Password y el ENTORNO EF
            Desarrollador p = new Desarrollador("P-" + i, "userP" + i, "userP" + i, entornoEF);
            hilos[contadorHilos] = new Thread(p);
            hilos[contadorHilos].start();
            contadorHilos++;
        }

        // 4. ESPERAR A TODOS (Crucial para el resumen)
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
        entornoDE.mostrarResumen();
        entornoEF.mostrarResumen();
    }
}
package tallerReparaciones;

import java.util.concurrent.Semaphore;

public class GestionaTaller {

    public static void main(String[] args) {
        // Iniciamos los semáforos
        Semaphore sElevadores = new Semaphore(3);
        Semaphore sPintura = new Semaphore(1);

        Taller taller = new Taller(sElevadores, sPintura);
        
        Thread[] hilos = new Thread[10];

        // 1. Lanzar 6 RAPIDOS
        for (int i = 1; i <= 6; i++) {
            String mat = "RAP-" + i;
            String token;
            
          
            if (i == 3) {
                token = "ERROR";
            } else {
                token = mat;
            }
            
            hilos[i-1] = new Thread(new Vehiculo(mat, token, "RAPIDO", taller));
            hilos[i-1].start();
        }

        // 2. Lanzar 4 COMPLETOS
        for (int i = 1; i <= 4; i++) {
            String mat = "COM-" + i;
            // Aquí todos tienen el token correcto (token = mat)
            hilos[5 + i] = new Thread(new Vehiculo(mat, mat, "COMPLETO", taller));
            hilos[5 + i].start();
        }

        // 3. Esperar a todos (Join)
        for (int i = 0; i < hilos.length; i++) {
            if (hilos[i] != null) {
                try {
                    hilos[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 4. Resultado final
        taller.mostrarResumen();
    }
}
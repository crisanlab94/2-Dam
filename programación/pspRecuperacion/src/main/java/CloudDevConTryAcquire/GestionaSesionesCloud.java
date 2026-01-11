package CloudDevConTryAcquire;

public class GestionaSesionesCloud {
	public static void main(String[] args) {
        // Creamos los entornos. El aforo se pasa al constructor y este crea el semáforo interno.
        EntornoCloud entornoDE = new EntornoCloud(Tipo.ED, 20);
        EntornoCloud entornoEF = new EntornoCloud(Tipo.EP, 8);

        // Lanzamos 35 hilos para el entorno DE (Aforo 20) -> 15 deberían ser rechazados
        for (int i = 1; i <= 35; i++) {
            new Thread(new Desarrollador("D-" + i, entornoDE)).start();
        }

        // Lanzamos 15 hilos para el entorno EF (Aforo 8) -> 7 deberían ser rechazados
        for (int i = 1; i <= 15; i++) {
            new Thread(new Desarrollador("P-" + i, entornoEF)).start();
        }
    }

}

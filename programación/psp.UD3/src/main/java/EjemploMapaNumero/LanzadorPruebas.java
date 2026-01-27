package EjemploMapaNumero;

public class LanzadorPruebas {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- INICIANDO BATERÍA DE PRUEBAS ---");

        // 1. Llenamos el mapa con varios jugadores
        new Thread(new HiloClientePruebas("SUMAR,Ana,10")).start();
        new Thread(new HiloClientePruebas("SUMAR,Juan,20")).start();
        new Thread(new HiloClientePruebas("SUMAR,Pedro,15")).start();
        new Thread(new HiloClientePruebas("SUMAR,Ana,5")).start(); // Ana debería tener 15

        // Damos un respiro de medio segundo para que se procesen
        Thread.sleep(500);

        // 2. Pedimos estadísticas y ganador
        new Thread(new HiloClientePruebas("STATS,null,0")).start();
        new Thread(new HiloClientePruebas("GANADOR,null,0")).start();

        // 3. Probamos filtros de Pares e Impares
        new Thread(new HiloClientePruebas("PARES,null,0")).start();
        new Thread(new HiloClientePruebas("IMPARES,null,0")).start();

        // 4. Borramos a alguien y volvemos a pedir el ganador
        new Thread(new HiloClientePruebas("BORRAR,Juan,0")).start();
        
        Thread.sleep(500);
        new Thread(new HiloClientePruebas("GANADOR,null,0")).start();
        
        System.out.println("--- TODAS LAS PETICIONES LANZADAS ---");
    }
}
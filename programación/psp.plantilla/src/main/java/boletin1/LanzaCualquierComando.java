package boletin1;

/*
 * EJERCICIO 11: Lanzar cualquier comando
 * ------------------------------------------------------------
 * Crea una clase con un método que reciba un comando y lo ejecute.
 */

public class LanzaCualquierComando {

    public void ejecutaComando(String[] comando) {
        try {
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process p = pb.start();
            int exit = p.waitFor();
            System.out.println("Código de salida: " + exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: LanzaCualquierComando <comando>");
            System.exit(1);
        }

        LanzaCualquierComando l = new LanzaCualquierComando();
        l.ejecutaComando(args);
        System.out.println("Termino");
    }
}


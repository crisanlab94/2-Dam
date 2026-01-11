package repaso6;

public class GestionaOficina {
    public static void main(String[] args) {
        Oficina oficina = new Oficina();

        // Lanzamos 5 clientes para 2 ventanillas. Verás que entran de 2 en 2.
        for (int i = 1; i <= 5; i++) {
            new Thread(new Cliente("Cliente-" + i, oficina)).start();
        }
    }
}
package repaso5;

public class GestionaRender {
    public static void main(String[] args) {
        ServidoraRender servidora = new ServidoraRender();

        // Lanzamos 6 proyectos con diferentes fotogramas
        for (int i = 1; i <= 6; i++) {
            int numFotogramas = (int) (Math.random() * 500) + 100;
            Thread t = new Thread(new Proyecto("Proyecto-" + i, numFotogramas, servidora));
            t.start();
        }
    }
}

//Thread.sleep(java.util.concurrent.ThreadLocalRandom.current().nextInt(1000, 3000)); (Duerme entre 1 y 3 segundos).
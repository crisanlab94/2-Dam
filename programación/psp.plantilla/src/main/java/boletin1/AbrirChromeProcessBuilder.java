package boletin1;

/*
 * EJERCICIO 2: Abrir Chrome con ProcessBuilder
 * ------------------------------------------------------------
 * Modifica el programa anterior para que abra Chrome con una URL,
 * pero esta vez usando ProcessBuilder.
 */

public class AbrirChromeProcessBuilder {
    public static void main(String[] args) {
        try {
            ProcessBuilder pb = new ProcessBuilder(
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
                "https://www.openai.com"
            );
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


package boletin1;

/*
 * EJERCICIO 1: Abrir Chrome con Runtime
 * ------------------------------------------------------------
 * Busca la ruta donde tienes el ejecutable para Chrome y crea 
 * un programa que usando Runtime abra una instancia de Chrome.
 * 
 * Después modifica para que abra una URL concreta.
 */

public class AbrirChromeRuntime {
    public static void main(String[] args) {
        try {
            // Ruta del ejecutable de Chrome 
            String rutaChrome = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
            
            // Abre Chrome vacío
            Runtime.getRuntime().exec(rutaChrome);

            // Abre Chrome con una URL concreta
            // Runtime.getRuntime().exec(new String[]{rutaChrome, "https://www.google.com"});

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

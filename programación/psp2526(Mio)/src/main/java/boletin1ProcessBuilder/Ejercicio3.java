package boletin1ProcessBuilder;

import java.io.IOException;

public class Ejercicio3 {

    public static void main(String[] args) {
        // Abrir una ventana de cmd visible que ejecute tasklist y se quede abierta
    	String []tasklist = {"cmd.exe","/C","start","cmd.exe","/k","tasklist"};
	ProcessBuilder pb = new ProcessBuilder (tasklist);
        try {
            pb.start(); // Esto abrirá una nueva ventana de cmd con tasklist
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }
}

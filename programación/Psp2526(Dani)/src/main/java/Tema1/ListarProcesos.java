package Tema1;

import java.io.IOException;

public class ListarProcesos {

    public static void main(String[] args) {
        // Abrir una ventana de cmd visible que ejecute tasklist y se quede abierta
    	String [] comando = {"cmd.exe", "/c", "start", "cmd.exe", "/k", "tasklist"};
        ProcessBuilder pb = new ProcessBuilder(comando);
        try {
            pb.start(); // Esto abrirá una nueva ventana de cmd con tasklist
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }
}

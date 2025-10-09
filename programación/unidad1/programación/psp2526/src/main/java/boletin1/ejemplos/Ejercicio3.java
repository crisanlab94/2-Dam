package boletin1.ejemplos;

import java.io.IOException;

public class Ejercicio3 {
	public static void main(String[] args) {
		
	String []tasklist = {"cmd.exe","/C","start","cmd.exe","/k","tasklist"};
	ProcessBuilder pb = new ProcessBuilder (tasklist);
	try {
		pb.start();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
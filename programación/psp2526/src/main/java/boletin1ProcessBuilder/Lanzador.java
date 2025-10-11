package Tema1;

import java.io.IOException;

public class Lanzador {
	private static final String directorioGenerarClasses = "target";
	private static final String rutaFicherosJava= "src\\main\\java";
	
	public static void main(String[] args) {
		//se genera el fichero .class donde yo le diga con el -d
		/*
		String[] comando = { "javac", "-d", directorioGenerarClasses, 
				rutaFicherosJava +" \\boletin1\\ejemplos\\Ejercicio4.java"};
		
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			Process proceso1=pb.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		Lanzador l1 = new Lanzador();
		l1.ejecutaProcesoCompila();
		l1.ejecutaJava();
	}
	
	
	//se ejecuta (leer el .class) la clase java
	public void ejecutaProcesoCompila() {
		String [] comando = {"javac", "-d", directorioGenerarClasses,rutaFicherosJava + "\\boletin1\\Ejercicio4.java"};
		ProcessBuilder pb = new ProcessBuilder (comando);
	
		
		try {
			//comunicar padre e hijo
			pb.redirectErrorStream(true);
			pb.inheritIO();
			Process p1 =pb.start();
			int exit = p1.waitFor();
			System.out.println(exit);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public void ejecutaJava() {
		String[] comando2 = {"java", "-cp", directorioGenerarClasses,"boletin1.Ejercicio4"};
		ProcessBuilder pb = new ProcessBuilder (comando2);
		
		try{
		pb.redirectErrorStream(true);
		pb.inheritIO();
		Process proceso = pb.start();
		int exit = proceso.waitFor();
		
		System.out.println(exit);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
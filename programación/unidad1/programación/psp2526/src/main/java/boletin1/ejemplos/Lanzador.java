package boletin1.ejemplos;

import java.io.IOException;

public class Lanzador {
	private static final String directorioGenerarClasses = "C:\\Users\\alumno\\Documents\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\psp2526\\target";
	private static final String rutaFicherosJava= "C:\\Users\\alumno\\Documents\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\psp2526\\src\\main\\java";
	
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
	}
	
	
	//se ejecuta (leer el .class) la clase java
	public void ejecutaProcesoCompila() {
		String [] comando = {"javac", "-d", directorioGenerarClasses,rutaFicherosJava + " \\boletin1\\ejemplos\\Ejercicio4.java"};
		ProcessBuilder pb = new ProcessBuilder (comando);
		try {
			Process proceso1=pb.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	public void ejecutaJava() {
		String[] comando = {"java", "-cp", "target/classes", rutaFicherosJava };
		ProcessBuilder pb = new ProcessBuilder (comando);
		pb.redirectErrorStream(true);
		pb.inheritIO();
		Process proceso = pb.start();
		int exit = proceso.waitFor();
		} catch
	}
	


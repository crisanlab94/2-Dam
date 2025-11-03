package boletin1;

//LanzadorCalculaSuma.java
public class LanzadorCalculaSuma {
 public void compilaClase() throws Exception {
     new ProcessBuilder("javac", "-d", "target/classes", "src/CalculaSuma.java").start().waitFor();
 }

 public void ejecuta(String tipo, String num) throws Exception {
     ProcessBuilder pb = new ProcessBuilder("java", "-cp", "target/classes", "CalculaSuma", tipo, num);
     pb.inheritIO();
     pb.start().waitFor();
 }

 public static void main(String[] args) throws Exception {
     LanzadorCalculaSuma l = new LanzadorCalculaSuma();
     l.compilaClase();
     l.ejecuta("par", "10");
     l.ejecuta("impar", "10");
 }
}

package boletin1;


	// Padre.java
	public class Padre {
	    public static void main(String[] args) {
	        try {
	            ProcessBuilder pb = new ProcessBuilder("java", "Hija");
	            pb.redirectErrorStream(true);
	            pb.inheritIO();
	            Process p = pb.start();
	            int codigo = p.waitFor();
	            System.out.println("Hija terminó con código: " + codigo);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

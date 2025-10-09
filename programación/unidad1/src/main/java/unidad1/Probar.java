package unidad1;

import java.io.File;

public class Probar {
	public static void main(String[] args) {
		ProbarPR app = new ProbarPR();
        
        File archivo = new File("C:\\Users\\alumno\\Documents\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\unidad1");
        
        app.getPropRecursive(archivo);
	}
	
	 public void getPropRecursive (File padre) {
	    	boolean existe = padre.exists();
	    	if (existe) {
	    		File [] listaficheros = padre.listFiles();
	    		for ( File fichero : listaficheros) {
	    			if (fichero.isFile()) {
	    				
	    				System.out.println(padre.getName());
	    			} else {
	    				System.out.println(fichero.getName());
	    				this.getPropRecursive(fichero);
	    			}
	    		}
	    	}
	    	
	    	
	    }

}

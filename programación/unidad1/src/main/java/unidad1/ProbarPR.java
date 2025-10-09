package unidad1;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class ProbarPR {
	private static final Logger logger = LogManager.getLogger(ProbarPR.class);



    public static void main(String[] args) {

    	ProbarPR app = new ProbarPR();

        app.gestionarFicheros();
        
        File archivo = new File("C:\\Users\\alumno\\Documents\\workspace-spring-tools-for-eclipse-4.31.0.RELEASE\\unidad1");
        
        app.getPropRecursive(archivo);

    }



    public void gestionarFicheros() {

        File directorio = crearDirectorio();

        File fichero1 = crearFichero(directorio, "fichero1.txt");

        File fichero2 = crearFichero(directorio, "fichero2.txt");



        establecerSoloLectura(fichero2);

        mostrarPermisos(fichero1);

        mostrarPermisos(fichero2);

        renombrarFichero(fichero1, new File(directorio, "renombrado.txt"));

        eliminarFichero(fichero2);

    }



    public File crearDirectorio() {

        File directorio = new File("miDirectorio");

        if (!directorio.exists()) {

            boolean creado = directorio.mkdir();

            logger.debug(creado ? "Directorio creado." : "No se pudo crear el directorio.");

        }

        return directorio;

    }



    public File crearFichero(File directorio, String nombre) {

        File fichero = new File(directorio, nombre);

        try {

            boolean creado = fichero.createNewFile();

            logger.debug(creado ? nombre + " creado." : nombre + " ya existe.");

        } catch (IOException e) {

            logger.debug("Error al crear el fichero " + nombre + ": " + e.getMessage());

        }

        return fichero;

    }



    public void establecerSoloLectura(File fichero) {

        boolean soloLectura = fichero.setReadable(true) && fichero.setWritable(false);

        logger.debug(soloLectura ? fichero.getName() + " es ahora solo lectura." : "No se pudo cambiar permisos.");

    }



    public void mostrarPermisos(File archivo) {

        logger.debug("Nombre: " + archivo.getName());

        logger.debug("   Se puede leer: " + archivo.canRead());

        logger.debug("   Se puede escribir: " + archivo.canWrite());

        logger.debug("   Ruta absoluta: " + archivo.getAbsolutePath());

    }



    public void renombrarFichero(File original, File nuevoNombre) {

        boolean renombrado = original.renameTo(nuevoNombre);

        logger.debug(renombrado ? original.getName() + " renombrado a " + nuevoNombre.getName() : "No se pudo renombrar.");

    }



    public void eliminarFichero(File fichero) {

        boolean borrado = fichero.delete();

        logger.debug(borrado ? fichero.getName() + " eliminado." : "No se pudo eliminar " + fichero.getName());

    }
    
    
    
    public void getPropRecursive (File padre) {
    	boolean existe = padre.exists();
    	if (existe) {
    		File [] listaficheros = padre.listFiles();
    		for ( File fichero : listaficheros) {
    			if (fichero.isFile()) {
    				System.out.println(fichero.getName());
    				System.out.println(padre.getName());
    			} else {
    				this.getPropRecursive(fichero);
    			}
    		}
    	}
    	
    	
    }

}
	



package Boletin2FicherosFoldes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiffFolder {
	private static final Logger logger = Logger.getLogger(DiffFolder.class.getName());
	
	private File folder1;
	private File folder2;

	public File getFolder1() {
		return folder1;
	}



	public File getFolder2() {
		return folder2;
	}



	public DiffFolder(File folder1, File folder2) {
		super();
		this.folder1 = folder1;
		this.folder2 = folder2;
	}

	@Override
	public String toString() {
		return "DiffFolder [folder1=" + folder1 + ", folder2=" + folder2 + "]";
	}

	public boolean esFolderValido(File folder) {
        return folder != null && folder.exists() && folder.isDirectory();
    }


	public void setFolders(File folder1, File folder2) throws GestionFicherosException {
		if (!esFolderValido(folder1)) {
			throw new GestionFicherosException("Directorio no válido.:" + folder1.getName());
		}
		if (!esFolderValido(folder2)) {
			throw new GestionFicherosException("Directorio no válido.:" + folder2.getName());
		}
		this.folder1=folder1;
		this.folder2=folder2;

	}

	 public Collection<ResultadoComparacion> compare() {
	        Collection<ResultadoComparacion> resultados = new ArrayList<>();
	       //nombreArchivo como clave y "archivo" como valor 
	        Map<String, File> archivos1 = new HashMap<String, File>();
	        Map<String, File> archivos2 = new HashMap<String, File>();

	        try {
	            if (esFolderValido(folder1)) {
	                for (File f : folder1.listFiles()) {
	                    if (f.isFile()) {
	                        archivos1.put(f.getName(), f);
	                    }
	                }
	            }

	            if (esFolderValido(folder2)) {
	                for (File f : folder2.listFiles()) {
	                    if (f.isFile()) {
	                        archivos2.put(f.getName(), f);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            logger.log(Level.SEVERE, "Error al acceder a los directorios", e);
	            return resultados;
	        }

	        //Mirar que carpetas tiene más archivos
	        Map<String, File> carpetaMayor;
	        Map<String, File> carpetaMenor;
	        boolean carpetaMayorEsFolder1;

	        if (archivos1.size() >= archivos2.size()) {
	            carpetaMayor = archivos1;
	            carpetaMenor = archivos2;
	            carpetaMayorEsFolder1 = true;
	        } else {
	            carpetaMayor = archivos2;
	            carpetaMenor = archivos1;
	            carpetaMayorEsFolder1 = false;
	        }

	        // Comparar archivos que estan en la carpeta mayor
	        for (Map.Entry<String, File> entry : carpetaMayor.entrySet()) {
	            String nombreArchivo = entry.getKey();
	            File archivoMayor = entry.getValue();
	            File archivoMenor = carpetaMenor.get(nombreArchivo);

	            ResultadoComparacion resultadoComparacion = new ResultadoComparacion();
	            resultadoComparacion.nombreArchivo = nombreArchivo;

	            try {
	                if (archivoMenor == null) {
	                    // El archivo no está en la carpeta menor
	                    resultadoComparacion.valorArchivo = carpetaMayorEsFolder1 ? Valor.FALTA_EN_2 : Valor.FALTA_EN_1;
	                } else {
	                    // El archivo esta en las 2 carpetas, miro cual es más nuevo
	                    long fechaMayor = archivoMayor.lastModified();
	                    long fechaMenor = archivoMenor.lastModified();

	                    if (fechaMayor == fechaMenor) {
	                        resultadoComparacion.valorArchivo = Valor.IGUALES;
	                    } else if (fechaMayor > fechaMenor) {
	                        resultadoComparacion.valorArchivo = carpetaMayorEsFolder1
	                                ? Valor.MENOS_NUEVO_EN_2
	                                : Valor.MENOS_NUEVO_EN_1;
	                    } else {
	                        resultadoComparacion.valorArchivo = carpetaMayorEsFolder1
	                                ? Valor.MENOS_NUEVO_EN_1
	                                : Valor.MENOS_NUEVO_EN_2;
	                    }
	                }
	            } catch (Exception e) {
	                logger.log(Level.WARNING, "Error comparando archivo: " + nombreArchivo, e);
	            }

	            resultados.add(resultadoComparacion); 
	        }

	        //Archivos que están solo en la carpeta menor
	        for (Map.Entry<String, File> entry : carpetaMenor.entrySet()) {
	            String nombreArchivo = entry.getKey();
	            if (!carpetaMayor.containsKey(nombreArchivo)) {
	                ResultadoComparacion resultadoComparacion = new ResultadoComparacion();
	                resultadoComparacion.nombreArchivo = nombreArchivo;
	                resultadoComparacion.valorArchivo = carpetaMayorEsFolder1 ? Valor.FALTA_EN_1 : Valor.FALTA_EN_2;
	                resultados.add(resultadoComparacion);
	            }
	        }

	        return resultados;
	    }
	}








package Boletin2FicherosFoldes;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Boletin2FicherosFoldesEnum.Valor;

public class DiffFolder {
	private File folder1;
	private File folder2;

	public File getFolder1() {
		return folder1;
	}

	public void setFolder1(File folder1) {
		this.folder1 = folder1;
	}

	public File getFolder2() {
		return folder2;
	}

	public void setFolder2(File folder2) {
		this.folder2 = folder2;
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

	private boolean esDirectorioValido(File carpeta) {
		return carpeta != null && carpeta.exists() && carpeta.isDirectory();
	}

	public void setFolders(File folder1, File folder2) throws GestionFicherosException {
		if (esDirectorioValido(folder1)) {
			this.folder1 = folder1;
		} else {
			throw new GestionFicherosException("Directorio no válido.:" + folder1.getName());
		}
		if (esDirectorioValido(folder2)) {
			this.folder2 = folder2;
		} else {
			throw new GestionFicherosException("Directorio no válido.:" + folder2.getName());
		}

	}

	public Map<String, File> obtenerArchivosCarpeta(File carpeta) {
		Map<String, File> archivosResultado = new HashMap<String, File>();
		File[] listaArchivos = carpeta.listFiles();
		for (File archivoActual : listaArchivos) {
			if (archivoActual.isFile()) {
				archivosResultado.put(archivoActual.getName(), archivoActual);
			}
		}
		return archivosResultado;
	}

	public Set<String> obtenerNombresArchivo(Map<String, File> mapaCarpeta1, Map<String, File> mapaCarpeta2) {
		Set<String> listaDeArchivosRepetidos = new HashSet<String>();
		listaDeArchivosRepetidos.addAll(mapaCarpeta1.keySet());
		listaDeArchivosRepetidos.addAll(mapaCarpeta2.keySet());
		return listaDeArchivosRepetidos;
	} 

	public Collection<ResultadoComparacion> compare() {
		Set<ResultadoComparacion> resultados = new HashSet<ResultadoComparacion>();
		Map<String, File> mapa1 = obtenerArchivosCarpeta(this.folder1);
		Map<String, File> mapa2 = obtenerArchivosCarpeta(this.folder2);
		boolean carpetaMayorEsFolder1 = mapa1.size() > mapa2.size();
		Set<String> todos = carpetaMayorEsFolder1 ? obtenerNombresArchivo(mapa1, mapa2)
				: obtenerNombresArchivo(mapa2, mapa1); 
		for (String nombre : todos) {
			resultados.add(compararArchivo(nombre, mapa1, mapa2, carpetaMayorEsFolder1));
		}
		return resultados;
	}
 

	private ResultadoComparacion compararArchivo(String nombre, Map<String, File> mapa1, Map<String, File> mapa2,
			boolean carpetaMayorEsFolder1) {
		Valor resultado = null;
		ResultadoComparacion comparacionArchivitos = null;
		File archivoMapa1 = mapa1.get(nombre);
		File archivoMapa2 = mapa2.get(nombre); 

		if (archivoMapa1 != null && archivoMapa2 != null) {
			long mod1 = archivoMapa1.lastModified();
			long mod2 = archivoMapa2.lastModified();
			if (mod1 == mod2) {
				resultado = Valor.IGUALES;
			} else if (mod1 > mod2) {
				resultado = carpetaMayorEsFolder1 ? Valor.MENOS_NUEVO_EN_1 : Valor.MENOS_NUEVO_EN_2;
			} else {
				resultado = carpetaMayorEsFolder1 ? Valor.MENOS_NUEVO_EN_2 : Valor.MENOS_NUEVO_EN_1;
			}
		} else if (archivoMapa1 == null) {
			resultado = carpetaMayorEsFolder1 ? Valor.FALTA_EN_1 : Valor.FALTA_EN_2;
		} else {
			resultado = carpetaMayorEsFolder1 ? Valor.FALTA_EN_2 : Valor.FALTA_EN_1;
		}

		return comparacionArchivitos = new ResultadoComparacion(nombre, resultado);
	}

}

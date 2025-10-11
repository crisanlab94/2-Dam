package Boletin2FicherosFoldes;

import java.io.File;

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

	public boolean validaFolder(File folder1) throws GestionFicherosException {
		boolean correcto = true;
		boolean existe1 = folder1.exists();
		if (!existe1 || !folder1.isDirectory()) {
			correcto = false;
		}
		return correcto;

	}

	public boolean validaFolder2(File folder2) throws GestionFicherosException {
		boolean correcto = true;
		boolean existe1 = folder2.exists();
		if (!existe1 || !folder2.isDirectory()) {
			correcto = false;
		}
		return correcto;

	}

	public void setFolders(File folder1, File folder2) throws GestionFicherosException {
		if (validaFolder(folder1)) {
			this.folder1 = folder1;
		} else {
			throw new GestionFicherosException("Directorio no válido.:" + folder1.getName());
		}
		if (validaFolder2(folder2)) {
			this.folder2 = folder2;
		} else { 
			throw new GestionFicherosException("Directorio no válido.:" + folder2.getName());
		}

	}

}

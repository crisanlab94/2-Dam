package controlador.peliculas;



import java.util.List;



import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import modelo.peliculas.Pelicula;
import utilidades.peliculas.XMLDomPeliculas;


public class GestionaPeliculas {

	public final static String rutaResources = "src/main/resources";

	private static final Logger logger = LogManager.getLogger(GestionaPeliculas.class);

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		XMLDomPeliculas pelis = new XMLDomPeliculas();

		try {

			List<Pelicula> listaPelis = pelis.leerPelissDesdeXML("peliculas.xml");

			for (Pelicula pelicula : listaPelis) {

				System.out.println(pelicula.toString()); 

			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}


}

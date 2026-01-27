package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import servicio.ServicioAlumno;



public class GestionaHQL {
	
	private static final Logger logger = LogManager.getLogger(GestionaHQL.class);
	public static void main(String[] args) {
		ServicioAlumno servicioAlumno = new ServicioAlumno();
		
		logger.info(servicioAlumno.devolverTodos());
		logger.info(servicioAlumno.devolverNombreEmail());
	}
		

}

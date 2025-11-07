package xml.dam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import xml.dam.modelo.Empleado;

public class GestionaEmpleadosXML {
	private static final Logger logger = LogManager.getLogger(GestionaEmpleadosXML.class);
 
	public static void main(String[] args) {
		XMLDomEmpleados xml = new XMLDomEmpleados();
		try {
			//Empleado e = xml.leerEmpleadoDesdeXML("empleado.xml");
			Empleado e = xml.leerEmpleadoDesdeXML("src/main/resources");
			logger.info(e.toString());
			System.out.println(e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package controlador;


import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.Enfrentamiento;
import util.DOMXMLTorneo;





public class PartidasTorneo {
	private static final Logger logger = LogManager.getLogger(PartidasTorneo.class);
	
	public static void main(String[] args) {
		DOMXMLTorneo torneo = new DOMXMLTorneo();
			try {
				Set<Enfrentamiento> enfrentamiento = torneo.leerEnfrentamientoDesdeXML("torneoGamer.xml");
				for (Enfrentamiento e : enfrentamiento) {
		              logger.info("Enfrentamiento: " + e.getDescripcion() + " /" + e.getEquipoGanador() + ""+ e.getId() + e.getFecha());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	              
		
	
			 }
	
}

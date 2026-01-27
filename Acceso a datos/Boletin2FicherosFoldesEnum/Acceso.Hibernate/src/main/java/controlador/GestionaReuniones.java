package controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.Acta;
import modelo.Persona;
import modelo.Reunion;
import modelo.Sala;
import repositorio.RepositoriActa;
import repositorio.RepositorioPersona;
import repositorio.RepositorioReunion;
import repositorio.RepositorioSala;

public class GestionaReuniones {
	private static final Logger logger = LogManager.getLogger(GestionaReuniones.class);
	public static void main(String[] args) {
		
		
		RepositorioReunion repoReunion = new RepositorioReunion();
		Reunion reunion1 = new Reunion(LocalDateTime.now().plusDays(3),"Proxima reunion 1");
		Reunion reunion2 = new Reunion(LocalDateTime.now().plusDays(3),"Proxima reunion 2");
		Reunion reunion3 = new Reunion(LocalDateTime.now().plusDays(3),"Proxima reunion 3");
		
		repoReunion.create(reunion1);
		
		List <Reunion> reuniones = repoReunion.getAllReuniones();
		for (Reunion reunion : reuniones) {
			logger.info(reunion);
		}
		
		
		RepositorioSala repoSala = new RepositorioSala();
		Sala sala1 = new Sala("Sala1");
		//repoSala.create(sala1);
		sala1.addReunion(reunion1);
		
		
		List <Sala> salas = repoSala.getAll();
		for (Sala sala : salas) {
			logger.info(sala);
		}
		
		Reunion reunionConSala = new Reunion(LocalDateTime.now().plusDays(3),"Proxima reunion",sala1);
		repoReunion.create(reunionConSala);
		
		RepositoriActa repoActa= new RepositoriActa();
		Acta acta1 = new Acta(reunion1);
		Acta acta3 = new Acta(reunion3);
		Acta acta4 = new Acta(reunionConSala);
		repoActa.mergeaObjeto(acta4);
		
		
		List <Acta> actas = repoActa.getAll();
		for (Acta acta : actas) {
			logger.info(acta);
		}
		
	
		// 1. Preparamos la reunión (asegurándonos de que esté persistida)
		Reunion nuevaReunion = new Reunion(LocalDateTime.now().plusDays(3),"Nueva Reunion",sala1);
		// Obtenemos la versión con ID
		nuevaReunion=repoReunion.mergeaObjeto(nuevaReunion);
		
		// 2. Creamos la persona
		Persona p = new Persona("12345629p", "Pepa Rosa", 22, "pepa@gmail.com", LocalDate.now(), "612345789");
		
		RepositorioPersona repoPersona= new RepositorioPersona();
		// La guardamos primero
		repoPersona.create(p);
		
		// 3. Establecemos la relación (Esto rellena la tabla intermedia)
		p.addReunion(nuevaReunion); 
		repoPersona.update(p); // Propagamos el cambio a la base de datos
		
		logger.info("Sincronización completada: Persona " + p.getNombreApellido() + " añadida a la reunión.");

		
		
		
		
		
	}

}

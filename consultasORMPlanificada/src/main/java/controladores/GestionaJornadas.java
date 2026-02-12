package controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import dao.EventoDao;
import dao.ParticipanteDao;
import dao.UbicacionDao;
import modelos.Evento;
import modelos.Participante;
import modelos.Ubicacion;


public class GestionaJornadas {  
	private static final Logger logger = LogManager.getLogger(GestionaJornadas.class);

	public static void main(String[] args) {
		ParticipanteDao participanteDao = new ParticipanteDao();
		EventoDao eventoDao = new EventoDao();
		UbicacionDao ubicacionDao = new UbicacionDao();

		Participante p1 = new Participante("12345678C", "Pepa", "Flores", "flores@gmail.com");
		participanteDao.create(p1);
		Participante p2 = new Participante("55345678C", "Rodolfo", "Renato", "rodolfoReno@gmail.com");
		participanteDao.create(p2);
		Participante p3 = new Participante("56745678C", "Beatriz", "Candelario", "bCandelario@gmail.com");
		participanteDao.create(p3);
		Participante p4 = new Participante("55775678C", "Rodolfo", "Ramposo", "rRamposo@gmail.com");
		participanteDao.create(p4);
		Participante p5 = new Participante("56712378C", "Beatriz", "Luengo", "bLuengo@gmail.com");
		participanteDao.create(p5);
		Participante p6 = new Participante("512345678C", "Carmelo", "Ramposo", "cRamposo@gmail.com");
		participanteDao.create(p6);

		Evento e1 = new Evento("Herramientas de programación con IA", "taller", LocalDateTime.now().plusDays(10), 120);
		Evento e2 = new Evento("Análisis de datos mediante inteligencia artificial", "conferencia",
				LocalDateTime.now().plusDays(10), 120);
		Evento e3 = new Evento("Confiar en la IA", "conferencia", LocalDateTime.now().plusDays(11), 90);
		Evento e4 = new Evento("Taller uso de la IA en el análisis de datos", "taller",
				LocalDateTime.now().plusDays(11), 120);

		
		e1.addAParticipante(p6);
		e2.addAParticipante(p6);
		e3.addAParticipante(p6);
		e4.addAParticipante(p6);

		e1.addAParticipante(p1);
		e2.addAParticipante(p1);
		e3.addAParticipante(p2);
		e4.addAParticipante(p2);

		e1.addAParticipante(p3);
		e2.addAParticipante(p4);
		e3.addAParticipante(p3);
		e4.addAParticipante(p4);

		e1.addAParticipante(p5);
		e2.addAParticipante(p5);
		e3.addAParticipante(p5);
		
		eventoDao.create(e1);
		eventoDao.create(e2);
		eventoDao.create(e3);
		eventoDao.create(e4);
		
		Ubicacion u1 = new Ubicacion("Salon de actos", "Calle Sin Salida 1");
		Ubicacion u2 = new Ubicacion("Sala laboratorio", "IES Torre Guzmanes aula 55A");
	


		ubicacionDao.create(u1);
		ubicacionDao.create(u2);
		
		u1.addEvento(eventoDao.get(1));
		u1.addEvento(eventoDao.get(2));
		u2.addEvento(eventoDao.get(3));
		u2.addEvento(eventoDao.get(4));
		
		ubicacionDao.mergeaObjeto(u1);
		ubicacionDao.mergeaObjeto(u2);


		/*List<Participante> participantes = participanteDao.getAll();
		for (Participante p : participantes) {
			logger.debug(p);
		}/*/
		List<Evento> eventos = eventoDao.getAll();
		for (Evento e : eventos) {
			logger.debug(e);
		}

		List<Ubicacion> ubicaciones = ubicacionDao.getAll();
		for (Ubicacion u : ubicaciones) {
			logger.debug(u);
		}
		
		
			
			// 1 
		/*
					logger.info("1.Participantes ordenados apellidos: " + participanteDao.getNombreYEmail());  */
				
					//2
					logger.info("Eventos más de 91 minutos: "+ eventoDao.getEventoMasMinutos());
					
					//4
					logger.info("Consulta Parametrizada: "+ eventoDao.getEventoUbicaion("Salon de actos"));

	}

}

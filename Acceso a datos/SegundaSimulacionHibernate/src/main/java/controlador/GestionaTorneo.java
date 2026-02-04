package controlador;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modelo.Equipo;
import modelo.Fase;
import modelo.Jugador;
import servicio.ServicioTorneo;



public class GestionaTorneo {
	private static final Logger logger = LogManager.getLogger(GestionaTorneo.class);
	public static void main(String[] args) {
		
		ServicioTorneo servicioTorneo = new ServicioTorneo();
		// Creamos los 4 equipos
		Equipo e1 = new Equipo("Alfa", 0);
		Equipo e2 = new Equipo("Beta", 0);
		Equipo e3 = new Equipo("Gamma", 0);
		Equipo e4 = new Equipo("Delta", 0);

		// Añadimos 2 jugadores a equipo 1
		e1.addJugador(new Jugador("111A", "Messi", "m@test.com", e1));
		e1.addJugador(new Jugador("111B", "Cristiano", "c@test.com", e1));
		// Añadimos 2 jugadores a equipo 2
		e2.addJugador(new Jugador("222A", "Neymar", "n@test.com", e2));
		e2.addJugador(new Jugador("222B", "Mbappe", "mb@test.com", e2));

		// Añadimos 2 jugadores a equipo 3
		e3.addJugador(new Jugador("333A", "Haaland", "h@test.com", e3));
		e3.addJugador(new Jugador("333B", "De Bruyne", "db@test.com", e3));

		// Añadimos 2 jugadores a equipo 4
		e4.addJugador(new Jugador("444A", "Vinicius", "v@test.com", e4));
		e4.addJugador(new Jugador("444B", "Bellingham", "be@test.com", e4));

		// Guardar
		servicioTorneo.addEquipo(e1);
		servicioTorneo.addEquipo(e2);
		servicioTorneo.addEquipo(e3);
		servicioTorneo.addEquipo(e4);
		
		//Fase semifinal
		Fase semifinal = new Fase("Semifinal", LocalDateTime.now());
		
		//Actualizamos puntos
		e1.setPuntosAcumulados(15);
		e2.setPuntosAcumulados(20); 
		e3.setPuntosAcumulados(10);
		e4.setPuntosAcumulados(25);
		
		//Añadimos equipos a la fase
		
		semifinal.addEquipo(e1);
		semifinal.addEquipo(e2);
		semifinal.addEquipo(e3);
		semifinal.addEquipo(e4);
		
		//Guardamos la fase en la base de datos
		servicioTorneo.addFase(semifinal);
		
		//Hay que actualizar los equipos para que se actualicen los puntos
		servicioTorneo.actualizarEquipo(e1);
		servicioTorneo.actualizarEquipo(e2);
		servicioTorneo.actualizarEquipo(e3);
		servicioTorneo.actualizarEquipo(e4);
		
		logger.info("Fase Semifinal registrada y equipos actualizados");
		
		//Los 2 mejores en la final
		// Obtenemos los dos mejores equipos desde la base de datos
		List<Equipo> finalistas = servicioTorneo.obtenerFinalistas();

		// Creamos la fase final
		Fase faseFinal = new Fase("Final", LocalDateTime.now().plusDays(2));

		// Añadimos los equipos que nos ha devuelto la consulta
		if (finalistas.size() >= 2) {
		    faseFinal.addEquipo(finalistas.get(0));
		    faseFinal.addEquipo(finalistas.get(1));
		}

		// Añadimos fase final
		servicioTorneo.addFase(faseFinal);

		logger.info("Finalistas recuperados por consulta: " + 
		                   finalistas.get(0).getNombre() + " y " + 
		                   finalistas.get(1).getNombre());
		
		
	}

}

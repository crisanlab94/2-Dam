package repositorio;


import modelo.Jugador;
import util.AbstractDao;

public class RepositorioJugador extends AbstractDao<Jugador> {

	public RepositorioJugador() {
		setClase(Jugador.class);
	}

}

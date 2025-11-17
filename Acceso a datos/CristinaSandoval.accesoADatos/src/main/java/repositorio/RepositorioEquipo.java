package repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Enfrentamiento;
import modelo.Equipo;
import util.TorneoException;

public class RepositorioEquipo {
	List<Equipo>equipos;

	public RepositorioEquipo(List<Equipo> equipos) {
		super();
		this.equipos = new ArrayList<Equipo>();
		
	}
	
	public List<Equipo> getListaEquipos() {
        return equipos;
    }
    
	
	public void agregarEquipo(Equipo equipo) throws TorneoException {
		boolean econtrado = false;
		Iterator<Enfrentamiento> ite = this.get).iterator();
		while (ite.hasNext() && !econtrado) {

			Enfrentamiento e = (Enfrentamiento) ite.next();

			List<Equipo> listaEquipo = e.get

			Iterator<Equipo> it = listaEquipo.iterator();

			while (it.hasNext() && !econtrado) {

				Equipo eq = (Equipo) it.next();

				if (eq.getCodigo().equals(e.g {

					throw new TorneoException("Este trabajador ya se asoció.");

				} else {

					listaEquipo.add(equipo);

					econtrado = true;

				}

			}

		}
	}
	

}

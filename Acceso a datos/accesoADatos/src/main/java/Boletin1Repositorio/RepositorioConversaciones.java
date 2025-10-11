package Boletin1Repositorio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Boletin1Excepcion.ConversacionException;
import Boletin1Modelo.Conversacion;
import Boletin1Modelo.TipoAgente;

public class RepositorioConversaciones implements IRepositorioConversaciones {
	private Set<Conversacion> listaConversacione;

	public RepositorioConversaciones() {
		super();
		this.listaConversacione = new HashSet<Conversacion>();
	}

	public Set<Conversacion> getListaConversacione() {
		return listaConversacione;
	}

	public void setListaConversacione(Set<Conversacion> listaConversacione) {
		this.listaConversacione = listaConversacione;
	}

	@Override
	public void agregaConversacion(TipoAgente tipo, String pregunta, String respuesta) {
		this.getListaConversacione().add(new Conversacion(pregunta, respuesta, tipo));
		// TODO Auto-generated method stub

	}

	@Override
	public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo, String pregunta)
			throws ConversacionException {
		Iterator<Conversacion> iteraConvers = this.getListaConversacione().iterator();
		Conversacion obtenerConversacion = null;
		boolean encontrado = false;

		while (iteraConvers.hasNext() && !encontrado) {
			Conversacion conversacion = (Conversacion) iteraConvers.next();
			if (conversacion.getFechaConversacion().equals(fecha) && conversacion.getPregunta().equals(pregunta)
					&& conversacion.getTipo().equals(tipo)) {
				obtenerConversacion = conversacion;
				encontrado = true;
			}

		}
		if (!encontrado) {
			throw new ConversacionException("Elemento no encontrado.");
		}

		// TODO Auto-generated method stub
		return obtenerConversacion;
	}

	@Override
	public boolean contieneConversacionConversacion(Conversacion conversacion) {
		boolean encontrado = false;
		if (this.getListaConversacione().contains(conversacion)) {
			encontrado = true;
		}
		// TODO Auto-generated method stub
		return encontrado;
	}

	@Override
	public void eliminaConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws ConversacionException {

		Conversacion conversacionAEliminar = null;
		try {
			conversacionAEliminar = this.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) {
			// TODO Auto-generated catch block
			throw e; 
		} 

		this.getListaConversacione().remove(conversacionAEliminar);

	}

	@Override
	public void incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta, double valoracion)
			throws ConversacionException {
		Conversacion conversacionActualizaValor = null; 
		try { 
			conversacionActualizaValor = this.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) { 
			// TODO Auto-generated catch block
			throw e;
		}  
		conversacionActualizaValor.setNumValoracionesPositivas(conversacionActualizaValor.getNumValoracionesPositivas() + 1);

		// TODO Auto-generated method stub
 
	}

	@Override
	public String toString() {
		return "RepositorioConversaciones [listaConversacione=" + listaConversacione + "]";
	}
	
	

}

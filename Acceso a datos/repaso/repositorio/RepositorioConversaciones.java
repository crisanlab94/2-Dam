package accesoADatos.repaso.repositorio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import accesoADatos.repaso.excepciones.ConversacionException;
import accesoADatos.repaso1.modelo.Conversacion;
import accesoADatos.repaso1.modelo.TipoAgente;

public class RepositorioConversaciones implements IRepositorioConversaciones {

	private Set<Conversacion> conversaciones;
	
	

	public RepositorioConversaciones() {
		super();
		this.conversaciones = new HashSet<Conversacion>();
	}
	
	

	public Set<Conversacion> getConversaciones() {
		return conversaciones;
	}



	public void setConversaciones(Set<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}



	@Override
	public void agregaConversacion(TipoAgente tipo, String pregunta, String respuesta) {
		this.conversaciones.add(new Conversacion(pregunta,respuesta,tipo));
		
	}


	
	@Override

	public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo, String pregunta)

			throws ConversacionException {

		Iterator<Conversacion> iteraConvers = this.getConversaciones().iterator();

		Conversacion obtenerConversacion = null;

		boolean encontrado = false;

		while (iteraConvers.hasNext() && !encontrado) {

			Conversacion conversacion = (Conversacion) iteraConvers.next();

			if (conversacion.getFechaConversacion().equals(fecha) && conversacion.getPregunta().equals(pregunta)

					&& conversacion.getTipo().equals(tipo)) {

				obtenerConversacion = conversacion;
				encontrado =true;

			}

			
			
		}
		if (!encontrado) {
			throw new ConversacionException("Error");
			
		}

		return obtenerConversacion;

	}

	@Override
	public boolean contieneConversacionConversacion(Conversacion conversacion) {
		boolean existe= false;
		if (this.getConversaciones().contains(conversacion)){
			existe=true;
		}
		return existe;
	}

	@Override
	public void eliminaConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws ConversacionException {
		Conversacion conver= null;
		try {
			conver = this.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) {
			throw e;
		}
		if (conver != null) {
			this.getConversaciones().remove(conver);
			
		}
		
		
	}

	@Override
	public void incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta, double valoracion)
			throws ConversacionException {
		Conversacion converActualiza= null;
		try {
			converActualiza = this.getConversacion(fecha, tipo, pregunta);
		} catch (ConversacionException e) {
			throw e;
		}
		converActualiza.setNumValoracionesPositivas(converActualiza.getNumValoracionesPositivas()+1 );
	}

	
}

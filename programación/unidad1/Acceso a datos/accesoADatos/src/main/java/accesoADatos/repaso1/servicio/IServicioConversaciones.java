package accesoADatos.repaso1.servicio;

import java.time.LocalDate;

import accesoADatos.repaso1.excepciones.ConversacionException;
import accesoADatos.repaso1.modelo.Conversacion;
import accesoADatos.repaso1.modelo.TipoAgente;

public interface IServicioConversaciones
	{
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta,
	String respuesta);
	public Conversacion getRecuperaConversacion(TipoAgente tipo, String
	pregunta, LocalDate fecha);
	public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo)
	throws ConversacionException;
	public boolean incrementaNumeroValoraciones(LocalDate fecha,
	TipoAgente tipo, String pregunta);
	public double getValoracionMediaParaHumanos();
	public double getValoracionMedidaParaBots;

}

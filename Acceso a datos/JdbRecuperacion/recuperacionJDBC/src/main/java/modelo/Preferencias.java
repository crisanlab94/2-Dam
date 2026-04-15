package modelo;

public class Preferencias {

	private boolean tema_oscuro;
	private String idioma;
	private boolean notificaciones_push;
	private boolean limite_datos_moviles;

	public Preferencias() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Preferencias(boolean tema_oscuro, String idioma, boolean notificaciones_push, boolean limite_datos_moviles) {
		super();
		this.tema_oscuro = tema_oscuro;
		this.idioma = idioma;
		this.notificaciones_push = notificaciones_push;
		this.limite_datos_moviles = limite_datos_moviles;
	}

	// Getters y Setters
	public boolean isTema_oscuro() {
		return tema_oscuro;
	}

	public void setTema_oscuro(boolean tema_oscuro) {
		this.tema_oscuro = tema_oscuro;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isNotificaciones_push() {
		return notificaciones_push;
	}

	public void setNotificaciones_push(boolean notificaciones_push) {
		this.notificaciones_push = notificaciones_push;
	}

	public boolean isLimite_datos_moviles() {
		return limite_datos_moviles;
	}

	public void setLimite_datos_moviles(boolean limite_datos_moviles) {
		this.limite_datos_moviles = limite_datos_moviles;
	}

	@Override
	public String toString() {
		return "Preferencias [tema_oscuro=" + tema_oscuro + ", idioma=" + idioma + ", notificaciones_push="
				+ notificaciones_push + ", limite_datos_moviles=" + limite_datos_moviles + "]";
	}
	
	
}

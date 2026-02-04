package modelo;

public class ModeloUnotoUno {
	//Quien "tiene" es la clase fuerte
	//La fuerte es la que no  tiene sentido sin la otra
	//La clase que no manda lleva mappedBy
	//La que si manda lleva @JoinColumn
	
	//Ejemplo Cada Equipo tiene un capitan(Jugador)
	//En el 1:1, ambos lados tienen un Objeto único
	//En el 1:1, Hibernate solo crea una columna extra en la tabla del lado fuerte

	
	/*
	 * // En la clase Equipo (Lado débil con mappedBy)
    public void setCapitan(Jugador capitan) {
    this.capitan = capitan;
    // IMPORTANTE: Le decimos al jugador que ahora es capitán de ESTE equipo
    if (capitan.getEquipoCapitaneado() != this) {
        capitan.setEquipoCapitaneado(this);
    }
}
	 */
	
	//Ejemplo Usuario-perfil, usuario manda porque no hay perfil si no hay usuario
	//Pasaporte-persona, pasaporte pertenece a persona, pasaporte(fuerte @JoinComun), persona(mappedBy)
	//Coche-motor, el coche "Tiene" motor, coche(fuerte)
}

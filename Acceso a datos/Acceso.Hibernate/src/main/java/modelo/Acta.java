package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="acta")
public class Acta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idActa;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn (name= "idReunion")
	private Reunion reunion;
	
	
	

	public Acta(Reunion reunion) {
		super();
		this.idActa = idActa;
		this.reunion = reunion;
	}
	
	//Importante constructor vacio

	public Acta() {
		super();
	}



	public int getIdActa() {
		return idActa;
	}

	public void setIdActa(int idActa) {
		this.idActa = idActa;
	}

	public Reunion getReunion() {
		return reunion;
	}

	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}

	@Override
	public String toString() {
		return "Acta [idActa=" + idActa + ", reunion=" + reunion + "]";
	}
	
	
	
	

}

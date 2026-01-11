package repositorioExamen;
import java.util.ArrayList;
import java.util.List;

import modeloExamen.Enfrentamiento;


public class EnfrentamientoRepositorio {
	    private List<Enfrentamiento> listaEnfrentamientos;

	    public EnfrentamientoRepositorio() {
	        this.listaEnfrentamientos = new ArrayList<>();
	    }

	    public void agregarEnfrentamiento(Enfrentamiento enf) {
	        this.listaEnfrentamientos.add(enf);
	    }

	    public List<Enfrentamiento> getListaEnfrentamientos() {
	        return listaEnfrentamientos;
	    }
	}



package servicioExamen;

import java.util.ArrayList;
import java.util.List;

import modeloExamen.Enfrentamiento;
import modeloExamen.EquipoExamen;
import repositorioExamen.EnfrentamientoRepositorio;
import repositorioExamen.EquipoRepositorio;




public class ServicioExamen {
	private EquipoRepositorio repoEquipos;
    private EnfrentamientoRepositorio repoEnfrentamientos;

    public ServicioExamen(EquipoRepositorio repoEquipos, EnfrentamientoRepositorio repoEnfrentamientos) {
        this.repoEquipos = repoEquipos;
        this.repoEnfrentamientos = repoEnfrentamientos;
    }
    
 // 1. getEquipo(código): Equipo
    public EquipoExamen getEquipo(String codigo) {
        return repoEquipos.obtenerEquipoPorCodigo(codigo);
    }

 // 2. agregarEquipo: lanza excepción si ya existe el código
    public void agregarEquipo(EquipoExamen e) throws Exception {
        // La lógica de la excepción ya la pusimos en el repo, el servicio la invoca
        repoEquipos.agregarEquipo(e);
    }
    
 // 3. agregarEnfrentamiento: lanza excepción si NO existe el código del equipo ganador
    public void agregarEnfrentamiento(Enfrentamiento enf) throws Exception {
        // Validamos que el equipo ganador exista en nuestro sistema
        if (repoEquipos.obtenerEquipoPorCodigo(enf.getEquipoGanador().getCodigo()) == null) {
            throw new Exception("ERROR: No se puede añadir el enfrentamiento. El equipo " 
                                + enf.getEquipoGanador().getCodigo() + " no existe.");
        }
        repoEnfrentamientos.agregarEnfrentamiento(enf);
    }
    
 // 4. getEnfrentamientosGanados(codigoEquipo): List<Enfrentamiento>
    // Este es el corazón del Apartado 3 también
    public List<Enfrentamiento> getEnfrentamientosGanados(String codigoEquipo) {
        List<Enfrentamiento> ganados = new ArrayList<>();
        
        for (Enfrentamiento enf : repoEnfrentamientos.getListaEnfrentamientos()) {
            // Comparamos el código del objeto Equipo que hay dentro del enfrentamiento
            if (enf.getEquipoGanador().getCodigo().equalsIgnoreCase(codigoEquipo)) {
                ganados.add(enf);
            }
        }
        return ganados;
    }
    
    // 5. Método para cargar los equipos en el repositorio (gestionando el error de JDG)
    public void cargarEquipos(List<EquipoExamen> listaEquiposXML) {
        // Llamamos al método del repositorio que ya tiene el try-catch y el log
        repoEquipos.agregarListaEquipos(listaEquiposXML);
    }

    // 6. Método para cargar enfrentamientos (Aquí es donde arreglamos el mapeo de String)
    public void cargarEnfrentamientos(List<Enfrentamiento> listaEnfXML) {
        for (Enfrentamiento enf : listaEnfXML) {
            // Guardamos el enfrentamiento tal cual viene del Main/Utiles
            repoEnfrentamientos.agregarEnfrentamiento(enf);
        }
    }

}

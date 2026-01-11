package repositorioExamen;


	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;

import modeloExamen.EquipoExamen;

		public class EquipoRepositorio {
			private static final Logger logger = LogManager.getLogger(EquipoRepositorio.class);
		    // Usamos un Map para que el código (G2, KOI...) sea la llave
		    private Map<String, EquipoExamen> mapaEquipos;

		    public EquipoRepositorio() {
		        this.mapaEquipos = new HashMap<>();
		    }

		    /**
		     * MÉTODO APARTADO 1: Agregar un solo equipo con control de duplicados.
		     * Si el código ya existe, lanza una excepción.
		     */
		    public void agregarEquipo(EquipoExamen e) throws Exception {
		        if (mapaEquipos.containsKey(e.getCodigo())) {
		            throw new Exception("ERROR: El código de equipo " + e.getCodigo() + " ya existe en el sistema.");
		        }
		        mapaEquipos.put(e.getCodigo(), e);
		    }

		    /**
		     * MÉTODO PARA EL APARTADO 2: Buscar un equipo por su código.
		     * Esto es lo que permite "unir" el enfrentamiento con el objeto equipo.
		     */
		    
		 //  Método para agregar la LISTA completa (gestiona el log)
		    // Este es el que resuelve lo del equipo "JDG" repetido
		    public void agregarListaEquipos(List<EquipoExamen> listaXML) {
		        for (EquipoExamen e : listaXML) {
		            try {
		                this.agregarEquipo(e);
		            } catch (Exception ex) {
		                // Esto es el LOG que pide el examen
		            	logger.error("Error al cargar equipo: {} -> {}", e.getCodigo(), ex.getMessage());
		            }
		        }
		    }
		    
		    public EquipoExamen obtenerEquipoPorCodigo(String codigo) {
		        return mapaEquipos.get(codigo);
		    }

		    public Map<String, EquipoExamen> getMapaEquipos() {
		        return mapaEquipos;
		    }
		}





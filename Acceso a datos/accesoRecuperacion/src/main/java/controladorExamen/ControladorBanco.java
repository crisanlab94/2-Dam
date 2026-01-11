package controladorExamen;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Util.DOMbanco;
import modeloExamen.BancoException;
import modeloExamen.CentroLogistico;
import modeloExamen.Tipo;
import modeloExamen.Trabajador;
import repositorioExamen.BancoAlimentos;
import servicioExamen.ServicioBancoAlimentos;



public class ControladorBanco {
	private static final Logger logger = LogManager.getLogger(ControladorBanco.class);

    public static void main(String[] args) {
        // 1. Inicializar la arquitectura
        BancoAlimentos bancosDeAlimentos = new BancoAlimentos();
        ServicioBancoAlimentos servicio = new ServicioBancoAlimentos(bancosDeAlimentos);
        DOMbanco dom = new DOMbanco();

        try {
            // --- APARTADO 2: CARGA DE DATOS ---
            logger.info("Iniciando carga desde bancoAlimentos.xml...");
            
            // Leemos los centros (que ya traen sus trabajadores dentro gracias al DOM)
            List<CentroLogistico> listaCentros = dom.leerBancoDesdeXML("bancoAlimentos.xml");
            
            // Cargamos los centros en el repositorio a través del servicio
            servicio.cargarCentros(listaCentros);
            
            logger.info("Carga completada. Centros cargados: " + bancosDeAlimentos.getMapaCentros().size());

            // --- PRUEBAS DE FUNCIONALIDADES (Apartado 1) ---

            // 1. Obtener datos de un centro por ID
            CentroLogistico sevilla = bancosDeAlimentos.recuperarCentro("CL01");
            if (sevilla != null) {
                logger.error("Centro encontrado: {} en {}. Trabajadores: {}", 
                             sevilla.getNombre(), sevilla.getCiudad(), sevilla.getListaTrabajadores().size());
            }

            // 2. Obtener un colaborador por DNI (Búsqueda global)
            String dniABuscar = "12345678A"; // Juan Pérez
            Trabajador t = servicio.buscarTrabajadorPorDni(dniABuscar);
            if (t != null) {
                logger.error("Colaborador encontrado: {} | Tipo: {}", t.getNombre(), t.getTipo());
            }

            // 3. PROBAR EXCEPCIÓN: Intentar agregar un trabajador que ya existe en ese centro
            logger.info("Probando duplicado para forzar BancoException...");
            if (t != null) {
                // Intentamos meter a Juan Pérez otra vez en CL01
                bancosDeAlimentos.agregarTrabajadorACentro("CL01", t);
            }

        } catch (BancoException e) {
            // Aquí capturamos el error de "Ya existe el trabajador" o "Ya existe el centro"
            logger.error("CONTROLADO: " + e.getMessage());
        } catch (Exception e) {
            // Aquí cualquier otro error (fichero no encontrado, error de parseo, etc.)
            logger.error("ERROR CRÍTICO: " + e.getMessage());
            e.printStackTrace();
        }
        
        logger.info("Fin del programa.");
        
        
        
   
     // --- APARTADO 4: GENERAR CSV DE VOLUNTARIOS ---
     try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.File(DOMbanco.rutaResources + "voluntarios.csv"))) {
         // 1. Escribir cabecera
         pw.println("NombreV, DniV, FechaNac, NombreC, identificadorC, CiudadC");

         // 2. Obtener solo los voluntarios
         List<Trabajador> voluntarios = servicio.obtenerColaboradoresPorTipo(Tipo.VOLUNTARIO);

         for (Trabajador v : voluntarios) {
             // Para sacar los datos del centro, se lo pedimos al repositorio usando el ID que guarda el trabajador
             CentroLogistico c = bancosDeAlimentos.recuperarCentro(v.getIdCentro());
             
             if (c != null) {
                 StringBuilder linea = new StringBuilder();
                 linea.append(v.getNombre()).append(", ");
                 linea.append(v.getDni()).append(", ");
                 linea.append(v.getFechaNacimiento()).append(", ");
                 linea.append(c.getNombre()).append(", ");
                 linea.append(c.getIdCentro()).append(", ");
                 linea.append(c.getCiudad());
                 
                 pw.println(linea.toString());
             }
         }
         logger.error("Fichero 'voluntarios.csv' generado correctamente en resources.");
         
     } catch (java.io.FileNotFoundException e) {
         logger.error("Error al crear el CSV: " + e.getMessage());
     }
    }

}

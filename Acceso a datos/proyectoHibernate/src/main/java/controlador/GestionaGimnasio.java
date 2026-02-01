package controlador;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import modelo.*;
import servicio.GimnasioServicio;
import java.util.List;

public class GestionaGimnasio {

    private static final Logger logger = LogManager.getLogger(GestionaGimnasio.class);

    public static void main(String[] args) {
        GimnasioServicio servicio = new GimnasioServicio();
        
        try {
            // ========================================================================
            // INSERCIÓN DE DATOS (ALTA DE OBJETOS Y RELACIONES)
            // ========================================================================
            logger.info("--- 1. INICIANDO DESPLIEGUE MASIVO DE DATOS ---");

            // DIRECCIONES (Se guardan por cascada al guardar el Gimnasio)
            Direccion d1 = new Direccion("Calle Mayor 1", "Madrid");
            Direccion d2 = new Direccion("Diagonal 100", "Barcelona");
            Direccion d3 = new Direccion("Gran Via 50", "Madrid");
            Direccion d4 = new Direccion("Calle Larios 5", "Malaga");

            // GIMNASIOS
            Gimnasio gymMadrid1 = new Gimnasio("Madrid Central", d1);
            Gimnasio gymBarna = new Gimnasio("Barna Power", d2);
            Gimnasio gymMadrid2 = new Gimnasio("Retiro Fitness", d3);
            Gimnasio gymMalaga = new Gimnasio("Costa del Sol Gym", d4);
            
            servicio.guardarGimnasio(gymMadrid1);
            servicio.guardarGimnasio(gymBarna);
            servicio.guardarGimnasio(gymMadrid2);
            servicio.guardarGimnasio(gymMalaga);

            // ACTIVIDADES
            Actividad yoga = new Actividad("Yoga");
            Actividad boxeo = new Actividad("Boxeo");
            Actividad zumba = new Actividad("Zumba");
            Actividad crossfit = new Actividad("Crossfit");
            
            servicio.guardarActividad(yoga);
            servicio.guardarActividad(boxeo);
            servicio.guardarActividad(zumba);
            servicio.guardarActividad(crossfit);

            // ENTRENADORES
            servicio.guardarEntrenador(new Entrenador("Pepe", "Yoga", Turno.MANANA, true,gymMadrid1));
            servicio.guardarEntrenador(new Entrenador("Ana", "Boxeo", Turno.TARDE, true,gymBarna));
            servicio.guardarEntrenador(new Entrenador("Luis", "Pilates", Turno.NOCHE, true,gymMadrid2));
            servicio.guardarEntrenador(new Entrenador("Marta", "Zumba", Turno.TARDE, false,gymMadrid1));
            servicio.guardarEntrenador(new Entrenador("Carlos", "Crossfit", Turno.MANANA, true,gymMalaga));

            // SOCIOS Y FICHAS MÉDICAS 
            Socio s1 = new Socio("Roberto", gymMadrid1, new FichaMedica(85.5, 1.80), TipoSuscripcion.ANUAL, true);
            Socio s2 = new Socio("Lucia", gymBarna, new FichaMedica(62.0, 1.65), TipoSuscripcion.MENSUAL, false);
            Socio s3 = new Socio("Marcos", gymMadrid1, new FichaMedica(95.2, 1.88), TipoSuscripcion.TRIMESTRAL, true);
            Socio s4 = new Socio("Sara", gymMalaga, new FichaMedica(58.5, 1.60), TipoSuscripcion.ANUAL, true);
            Socio s5 = new Socio("Raul", gymMadrid2, new FichaMedica(75.0, 1.75), TipoSuscripcion.MENSUAL, false);

            servicio.guardarSocio(s1);
            servicio.guardarSocio(s2);
            servicio.guardarSocio(s3);
            servicio.guardarSocio(s4);
            servicio.guardarSocio(s5);

            // INSCRIPCIONES EN ACTIVIDAD
            servicio.inscribirSocioEnActividad(s1.getId(), yoga.getId());
            servicio.inscribirSocioEnActividad(s1.getId(), crossfit.getId());
            servicio.inscribirSocioEnActividad(s2.getId(), yoga.getId());
            servicio.inscribirSocioEnActividad(s3.getId(), boxeo.getId());
            servicio.inscribirSocioEnActividad(s5.getId(), zumba.getId());

            logger.info("--- LLENADO Y VINCULACIÓN FINALIZADA ---");

            // ========================================================================
            // CONSULTAS DE LECTURA 
            // ========================================================================
            logger.info("--- EJECUTANDO BATERÍA DE CONSULTAS (HQL) ---");

         // Restringir a 1 elemento
            Gimnasio gPri = servicio.obtenerPrimerGimnasio();
            if (gPri != null) logger.info("Consulta (Primer gym registrado): {}", gPri.getNombre());

            // Un campo de una tabla
            logger.info("Consulta  Nombres de todos los socios: {}", servicio.obtenerNombresDeTodosLosSocios());

            // Dos campos o más (NEW Entrenador con nombre y especialidad)
            List<Entrenador> infoCoches = servicio.obtenerNombreYEspecialidadEntrenadores();
            for(Entrenador e : infoCoches) logger.info("Consulta Entrenador-Especialidad: {} - {}", e.getNombre(), e.getEspecialidad());

            // Consultas Parametrizadas
            logger.info("Consulta (Parametrizada - Numero de gym en Madrid): {} gyms", servicio.buscarGimnasiosPorCiudad("Madrid").size());
            logger.info("Consulta (Parametrizada - Numero de turno de Tarde): {} entrenadores", servicio.obtenerEntrenadoresPorTurno(Turno.TARDE).size());

            

            // Filtrar y Ordenar (3 últimos socios por ID desc)
            List<Socio> ultimos3 = servicio.obtener3UltimosSocios();
            for (Socio s : ultimos3) logger.info("Consulta (3 ultimos socios): ID {} - {}", s.getId(), s.getNombre());

            // Fichas en rango (Between)
            List<FichaMedica> fichas = servicio.buscarFichasPorRangoDePeso(60.0, 90.0);
            logger.info("Consulta numero (Rango Peso 60-90kg): {} fichas encontradas", fichas.size());
            
            // Actividades populares (Count )
            List<Actividad> populares = servicio.obtenerActividadesPopulares(1);
            for (Actividad a : populares) logger.info("Actividad popular: {}", a.getNombre());

            // AVG  Fichas en rango (Between)
            List<FichaMedica> fichasRango = servicio.buscarFichasPorRangoDePeso(60.0, 90.0);
            for (FichaMedica f : fichasRango) logger.info("Ficha en rango -> Peso: {}kg", f.getPeso());

            //Peso máximo
            Double pMax=servicio.obtenerPesoMaximoRegistrado();
            logger.info("Peso máximo registrado: "+pMax);
            
            // ========================================================================
            // ACTUALIZACIONES (CADA MODELO )
            // ========================================================================
            logger.info("--- 3. ACTUALIZANDO UN OBJETO DE CADA MODELO ---");

            //  Socio: Cambiamos suscripción a Lucía (s2)
            s2.setSuscripcion(TipoSuscripcion.ANUAL);
            servicio.actualizarSocio(s2);

            // Entrenador: Activamos a Marta (buscándola por nombre)
            Entrenador coachMarta = servicio.obtenerEntrenadoresPorTurno(Turno.TARDE).get(1); 
            coachMarta.setEstaActivo(true);
            servicio.actualizarEntrenador(coachMarta);

            // Actividad: Renombrar Yoga
            yoga.setNombre("Yoga Meditativo");
            servicio.actualizarActividad(yoga);

            // Direccion: Cambiar calle de d1 (Calle Mayor)
            d1.setCalle("Calle Mayor Modificada 500");
            servicio.actualizarDireccion(d1);

            // Ficha Médica: Añadir observación a la ficha de Marcos (s3)
            FichaMedica fmMarcos = s3.getFichaMedica();
            fmMarcos.setObservaciones("Mejorando resistencia");
            servicio.actualizarFichaMedica(fmMarcos);

            // Gimnasio: Actualizar Málaga por Criteria 
            servicio.actualizarNombreGymCriteria(gymMalaga.getId(), "Costa del Sol Luxury Fit");
            
            logger.info("Actualizaciones completadas para los 6 modelos.");

            // ========================================================================
            // ELIMINACIONES (DE CADA MODELO)
            // ========================================================================
          
            // ELIMINAR ACTIVIDAD (Boxeo)
            servicio.borrarSocioDeActividad(s3.getId(), boxeo.getId()); 
            servicio.eliminarActividad(boxeo);
            logger.info("Actividad 'Boxeo' eliminada.");

            // ELIMINAR ENTRENADOR (Carlos)
            // Lo buscamos para asegurarnos de tener la instancia fresca de la BD
            List<Entrenador> coachesC = servicio.buscarEntrenadoresPorEspecialidad("Crossfit");
            if(!coachesC.isEmpty()) {
                servicio.eliminarEntrenador(coachesC.get(0));
                logger.info("Entrenador 'Carlos' eliminado.");
            }

            // ELIMINAR SOCIO y FICHA MÉDICA (Raúl - s5)
            servicio.borrarSocioDeActividad(s5.getId(), zumba.getId());
            servicio.eliminarSocio(s5); 
            logger.info("Socio 'Raul' eliminado.");

            // ELIMINAR GIMNASIO (gymMadrid2 - Retiro Fitness)
            // Antes de borrar el gym, quitamos este gym de sus entrenadores
            
            List<Entrenador> staff = servicio.obtenerEntrenadoresActivos();
            for(Entrenador e : staff) {
                if(e.getGimnasio() != null && e.getGimnasio().getId() == gymMadrid2.getId()) {
                    e.setGimnasio(null);
                    servicio.actualizarEntrenador(e);
                }
            }
            servicio.eliminarGimnasio(gymMadrid2);
            logger.info("Gimnasio 'Retiro Fitness' eliminado (personal desvinculado).");

            // ELIMINAR DIRECCIÓN (d4 - Málaga)
            gymMalaga.setDireccion(null);
            servicio.actualizarGimnasio(gymMalaga);
            servicio.eliminarDireccion(d4);
            logger.info("Dirección de Málaga eliminada.");

            // BORRADO CRITERIA (Roberto)
            servicio.borrarSocioPorNombreCriteria("Roberto");
            logger.info("Socio 'Roberto' eliminado mediante Criteria.");
            logger.info("Socio 'Roberto' eliminado mediante CriteriaBuilder.");
            logger.info("--- TODOS LOS MODELOS ACTUALIZADOS Y ELIMINADOS CORRECTAMENTE ---");
  

        } catch (Exception e) {
            logger.error("Error crítico en la ejecución del sistema: ", e);
            
        }
        
    }
}
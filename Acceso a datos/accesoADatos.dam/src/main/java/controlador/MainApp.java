package controlador;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import modelo.InteraccionAgente;
import repositorio.RepositorioInteracciones;
import servicios.ServicioEstadisticaImpl;

public class MainApp {
    public static void main(String[] args) {
        try {
            ServicioEstadisticaImpl servicio = new ServicioEstadisticaImpl();

          
            List<InteraccionAgente> lista = servicio.cargarRegistrosDesdeJSON();


            RepositorioInteracciones repo = new RepositorioInteracciones();
            for (InteraccionAgente inter : lista) {
                repo.agregaInteraccionARegistro(inter);
            }

            List<InteraccionAgente> registros = repo.getRegistros();

            
            InteraccionAgente mejor = servicio.obtenerInteraccionConMejorValoracion(registros);
            System.out.println("Interaccion con mejor valoración:");
            System.out.println(mejor);

            
            if (!registros.isEmpty()) {
                InteraccionAgente primera = registros.get(0);
                repo.incrementaNumeroValoraciones(primera.getIdentificador(),
                        primera.getNumValoracionesPositivas() + 1);
                repo.actualizaPorcentajeInteraccion(primera.getIdentificador(),
                        primera.getPorcentajeAcierto() + 5);
            }

           
            List<InteraccionAgente> filtradas = servicio.obtenerInteraccionesAciertoMayorQueOrdenadas(70.0,
                    registros);
            System.out.println("Interacciones con % acierto > 70:");
            for (InteraccionAgente inter : filtradas) {
                System.out.println(inter);
            }

      
            Collections.sort(registros, Comparator.comparingInt(InteraccionAgente::getIdentificador));
            servicio.grabarFicheroCSV("salidaOrdenada.csv", registros);
            System.out.println("CSV generado en salidaOrdenada.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

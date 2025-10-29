package servicios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import modelo.InteraccionAgente;
import modelo.TipoAgente;

public class ServicioEstadisticaImpl {

    public InteraccionAgente obtenerInteraccionConMejorValoracion(Collection<InteraccionAgente> interacciones) {
        InteraccionAgente mejor = null;
        int maxValor = -1;
        for (InteraccionAgente inter : interacciones) {
            if (inter.getNumValoracionesPositivas() > maxValor) {
                maxValor = inter.getNumValoracionesPositivas();
                mejor = inter;
            }
        }
        return mejor;
    }

    public double calcularTiempoMedioPorTipo(TipoAgente tipoAgente, Collection<InteraccionAgente> interacciones) {
        double suma = 0;
        int contador = 0;
        for (InteraccionAgente inter : interacciones) {
            if (inter.getTipo() == tipoAgente) {
                suma += inter.getTiempoEjecucion();
                contador++;
            }
        }
        double resultado = 0;
        if (contador > 0) resultado = suma / contador;
        return resultado;
    }

public double calcularPorcentajeAciertoMedioPorTipo(TipoAgente tipoAgente, Collection<InteraccionAgente> interacciones) {
    double suma = 0;
    int contador = 0;
    for (InteraccionAgente inter : interacciones) {
        if (inter.getTipo() == tipoAgente) {
            suma += inter.getPorcentajeAcierto();
            contador++;
        }
    }
    double resultado = 0;
    if (contador > 0) resultado = suma / contador;
    return resultado;
}

    public List<InteraccionAgente> obtenerInteraccionesAciertoMayorQueOrdenadas(double porcentaje, Collection<InteraccionAgente> interacciones) {
        List<InteraccionAgente> filtradas = new ArrayList<>();
        for (InteraccionAgente inter : interacciones) {
            if (inter.getPorcentajeAcierto() > porcentaje) {
                filtradas.add(inter);
            }
        }

        filtradas.sort(new java.util.Comparator<InteraccionAgente>() {
            @Override
            public int compare(InteraccionAgente a, InteraccionAgente b) {
                int cmp = Double.compare(b.getPorcentajeAcierto(), a.getPorcentajeAcierto());
                if (cmp != 0) return cmp;
                return a.getTipo().compareTo(b.getTipo());
            }
        });

        return filtradas;
    }
      

    public Map<TipoAgente, List<InteraccionAgente>> agruparInteraccionesPorTipo(Collection<InteraccionAgente> interacciones) {
        Map<TipoAgente, List<InteraccionAgente>> mapa = new HashMap<>();
        for (InteraccionAgente inter : interacciones) {
            if (!mapa.containsKey(inter.getTipo())) {
                mapa.put(inter.getTipo(), new ArrayList<>());
            }
            mapa.get(inter.getTipo()).add(inter);
        }
        return mapa;
    }

  
  

    public void grabarFicheroCSV(String rutaArchivo, Collection<InteraccionAgente> interacciones) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
        for (InteraccionAgente inter : interacciones) {
            bw.write(inter.toString());
            bw.newLine();
        }
        bw.close();
    }

    public List<InteraccionAgente> cargarRegistrosDesdeJSON() throws IOException {
        Gson gson = new Gson();
        InputStream inputStream = getClass().getResourceAsStream("/interaciones.json");
        if (inputStream == null) {
            throw new IOException("Archivo interaciones.json no encontrado en resources");
        }

        Reader reader = new InputStreamReader(inputStream);
        List<InteraccionAgente> lista = gson.fromJson(reader, new TypeToken<List<InteraccionAgente>>(){}.getType());
        reader.close();
        return lista;
    }

    
   
}

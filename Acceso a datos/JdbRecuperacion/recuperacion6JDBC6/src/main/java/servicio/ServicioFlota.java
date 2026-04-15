package servicio;

import java.util.ArrayList;
import java.util.List;
import modelo.AppException;
import modelo.Nave;
import modelo.Tripulante;
import repositorio.RepositorioNave;
import repositorio.RepositorioTripulante;

public class ServicioFlota {
    private final RepositorioNave repoNave;
    private final RepositorioTripulante repoTrip;

    public ServicioFlota(RepositorioNave repoNave, RepositorioTripulante repoTrip) {
        this.repoNave = repoNave;
        this.repoTrip = repoTrip;
    }

    /**
     * APARTADO 1: Obtener flota completa (Hidratación)
     * Une las naves con sus tripulantes en memoria.
     * Aqui es donde metemos la lista de tripulantes que aparece en la entidad Nave
     */
    public List<Nave> obtenerFlota() throws AppException {
        List<Nave> naves = repoNave.getNaves();
        List<Tripulante> tripulantes = repoTrip.getTripulantes();

        // 1. Limpiamos las listas de las naves para evitar duplicados si se llama varias veces
        for (Nave n : naves) {
            n.getTripulacion().clear();
        }

        // 2. Repartimos los tripulantes en sus naves correspondientes (Hidratación)
        for (Tripulante t : tripulantes) {
            // Buscamos la nave usando el método que ya tienes en el repo
            Nave naveDestino = repoNave.buscarPorId(t.getIdNave());
            
            if (naveDestino != null) {
                naveDestino.getTripulacion().add(t);
            }
        }
        
        return naves; 
    }

    /**
     * APARTADO 3: Filtrar tripulantes por rango (Ordenados por nombre)
     * Ahora usamos el método del Repositorio que hace el trabajo con SQL.
     */
    public List<Tripulante> getTripulantesPorRango(String rango) throws AppException {
        // Llamamos directamente al método del repositorio que ya filtra y ordena
        List<Tripulante> resultado = repoTrip.readPorRango(rango);
        
        return resultado; // Un solo punto de salida
    }

    /**
     * APARTADO 4: Registrar tripulante con validación de Nave
     */
    public void registrarTripulante(Tripulante t) throws AppException {
        // Validamos que la nave asignada exista de verdad
        Nave existe = repoNave.buscarPorId(t.getIdNave());
        
        if (existe == null) {
            throw new AppException("Error: La nave con ID " + t.getIdNave() + " no existe.");
        }
        
        repoTrip.guardar(t);
    }

    /**
     * APARTADO 5: Actualizar rango
     */
    public void ascender(int id, String rango) throws AppException {
        repoTrip.actualizarRango(id, rango);
    }

    /**
     * APARTADO 7: Eliminar inactivos de una nave
     */
    public void limpiarNave(int idNave) throws AppException {
        repoTrip.eliminarInactivos(idNave);
    }
    
    /**
     * Método pasarela para eliminar una nave.
     * Se añade aquí para que el Controlador pueda usarlo.
     */
    public void eliminarNave(int id) throws AppException {
        // Simplemente le pasamos la pelota al repositorio
        repoNave.eliminar(id);
    }
}
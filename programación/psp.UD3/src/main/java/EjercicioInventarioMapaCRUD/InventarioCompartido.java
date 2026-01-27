package EjercicioInventarioMapaCRUD;

import java.util.*;

public class InventarioCompartido {

    private Map<String, Integer> inventario;

    public InventarioCompartido() {
        inventario = new HashMap<>();

        inventario.put("manzanas", 10);
        inventario.put("naranjas", 5);
        inventario.put("peras", 8);
    }

    // ============================
    // CREATE
    // ============================
    public synchronized String crear(String producto, int cantidad) {
        String mensaje = "Producto creado correctamente.";

        if (inventario.containsKey(producto)) {
            mensaje = "El producto ya existe.";
        } else {
            inventario.put(producto, cantidad);
        }

        return mensaje;
    }

    // ============================
    // READ
    // ============================
    public synchronized String leer(String producto) {
        String resultado = "Producto no encontrado.";

        if (inventario.containsKey(producto)) {
            resultado = producto + ": " + inventario.get(producto);
        }

        return resultado;
    }

    // ============================
    // UPDATE
    // ============================
    public synchronized String actualizar(String producto, int cantidad) {
        String mensaje = "Producto actualizado.";

        if (inventario.containsKey(producto)) {
            inventario.put(producto, cantidad);
        } else {
            mensaje = "El producto no existe.";
        }

        return mensaje;
    }

    // ============================
    // DELETE
    // ============================
    public synchronized String eliminar(String producto) {
        String mensaje = "Producto eliminado.";

        if (inventario.containsKey(producto)) {
            inventario.remove(producto);
        } else {
            mensaje = "El producto no existe.";
        }

        return mensaje;
    }

    // ============================
    // LISTAR (READ ALL)
    // ============================
    public synchronized String listar() {
        String resultado = "";

        Set<String> claves = inventario.keySet();
        Iterator<String> it = claves.iterator();

        while (it.hasNext()) {
            String prod = it.next();
            resultado = resultado + prod + ": " + inventario.get(prod) + " | ";
        }

        return resultado;
    }
}

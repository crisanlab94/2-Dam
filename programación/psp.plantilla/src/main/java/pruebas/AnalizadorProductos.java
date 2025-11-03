package pruebas;

import java.io.*;
import java.util.*;

/**
 * Clase AnalizadorProductos
 * -------------------------
 * Lee un fichero con formato: provincia;producto;cantidad
 * Si el último argumento es "ordenar", ordena por nombre y provincia.
 * Si es "no_ordenar", mantiene el orden original.
 *
 * Genera:
 *  resultadoProductos.txt (fichero general)
 *  Un fichero por cada producto (ej: aceite.txt, jamon.txt...)
 */
public class AnalizadorProductos {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Uso: java pruebas.AnalizadorProductos <ficheroEntrada> <ficheroSalida> <ordenar|no_ordenar>");
            System.exit(1);
        }

        String ficheroEntrada = args[0];
        String ficheroSalida = args[1];
        String modoOrden = args[2];

        List<Producto> productos = new ArrayList<>();

        // 1️⃣ Leer fichero
        try (BufferedReader br = new BufferedReader(new FileReader(ficheroEntrada))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    String provincia = partes[0].trim();
                    String nombre = partes[1].trim();
                    int cantidad = Integer.parseInt(partes[2].trim());
                    productos.add(new Producto(provincia, nombre, cantidad));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error leyendo el fichero: " + ficheroEntrada + " (" + e.getMessage() + ")");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error leyendo el fichero: " + e.getMessage());
            System.exit(1);
        }

        // 2️⃣ Ordenar si se solicita
        if (modoOrden.equalsIgnoreCase("ordenar")) {
            Collections.sort(productos, new Comparator<Producto>() {
                public int compare(Producto p1, Producto p2) {
                    int cmp = p1.nombre.compareToIgnoreCase(p2.nombre);
                    if (cmp != 0) return cmp;
                    return p1.provincia.compareToIgnoreCase(p2.provincia);
                }
            });
            System.out.println("Productos ordenados alfabéticamente.");
        } else {
            System.out.println("Manteniendo el orden original del fichero.");
        }

        // 3️⃣ Guardar resultado general
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
            bw.write("===== LISTA DE PRODUCTOS =====\n");
            for (Producto p : productos) {
                bw.write(p.toString());
                bw.newLine();
            }

            bw.write("\n===== PRODUCTOS CON CANTIDAD > 5 =====\n");
            for (Producto p : productos) {
                if (p.cantidad > 5) {
                    bw.write(p.toString());
                    bw.newLine();
                }
            }

            // Orden por cantidad descendente
            List<Producto> desc = new ArrayList<>(productos);
            Collections.sort(desc, new Comparator<Producto>() {
                public int compare(Producto a, Producto b) {
                    return Integer.compare(b.cantidad, a.cantidad);
                }
            });

            bw.write("\n===== ORDENADOS POR CANTIDAD (MAYOR A MENOR) =====\n");
            for (Producto p : desc) {
                bw.write(p.toString());
                bw.newLine();
            }

            // Orden por cantidad ascendente
            List<Producto> asc = new ArrayList<>(productos);
            Collections.sort(asc, new Comparator<Producto>() {
                public int compare(Producto a, Producto b) {
                    return Integer.compare(a.cantidad, b.cantidad);
                }
            });

            bw.write("\n===== ORDENADOS POR CANTIDAD (MENOR A MAYOR) =====\n");
            for (Producto p : asc) {
                bw.write(p.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error escribiendo el fichero general: " + e.getMessage());
        }

        // 4️⃣ Crear un fichero individual por producto
        for (Producto p : productos) {
            String nombreFichero = ficheroSalida.substring(0, ficheroSalida.lastIndexOf("/") + 1)
                                   + p.nombre.toLowerCase().replaceAll("\\s+", "_") + ".txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero, true))) {
                bw.write(p.toString());
                bw.newLine();
            } catch (IOException e) {
                System.err.println("Error escribiendo " + nombreFichero + ": " + e.getMessage());
            }
        }

        // 5️⃣ Fin
        System.out.println("Análisis completado correctamente.");
        System.out.println("Fichero general: " + ficheroSalida);
        System.out.println("Ficheros individuales generados por producto.");
    }

    // Clase interna Producto
    static class Producto {
        String provincia;
        String nombre;
        int cantidad;

        public Producto(String provincia, String nombre, int cantidad) {
            this.provincia = provincia;
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        public String toString() {
            return String.format("%-12s | %-10s | %3d", provincia, nombre, cantidad);
        }
    }
}

package microservicios.primerProyectoSpring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import microservicios.primerProyectoSpring.modelo.Product;
import microservicios.primerProyectoSpring.repository.ProductoRepositorio;

@Service 
public class ProductService {

    @Autowired 
    private ProductoRepositorio productoRepositorio;

    //para guardar solo uno
    public Product guardarProducto(Product producto) {
        // Aquí va la lógica (ej: poner la fecha de creación)
        return productoRepositorio.save(producto);
    }
    
    //para guardar varios
    public List<Product> guardarVarios(List<Product> productos) {
        return productoRepositorio.saveAll(productos);
    }
    
    //Verlos todos
    public List<Product> obtenerTodos() {
        return productoRepositorio.findAll(); // findAll() ya viene creado en el JpaRepository
    }
    
 // Borrar
    public void borrarPorId(Long id) {
        productoRepositorio.deleteById(id);
    }
    
    //Actualizar
    public Product actualizarProducto(Long id, Product datosNuevos) {
        Product resultado = null; 

        if (productoRepositorio.existsById(id)) { // Comprobamos si existe
            datosNuevos.setId(id); // Le ponemos el ID para que Hibernate sepa que es un UPDATE
            resultado = productoRepositorio.save(datosNuevos); // Guardamos
        }

        return resultado; 
    }
    
    
}
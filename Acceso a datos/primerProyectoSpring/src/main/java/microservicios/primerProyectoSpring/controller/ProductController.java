package microservicios.primerProyectoSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservicios.primerProyectoSpring.modelo.Product;
import microservicios.primerProyectoSpring.service.ProductService;


@RestController
@RequestMapping("/api/productos")
public class ProductController {
	
	@Autowired
    private ProductService productService;

	/*Para probar conexión
    @GetMapping("/test")
    public String probar() {
        return "¡Microservicio de Cristina funcionando y conectado a Docker!";
    }*/
	
	// Método para AÑADIR (POST)
    @PostMapping 
    public Product crearProducto(@RequestBody Product nuevoProducto) {
        // @RequestBody convierte el JSON que enviamos en un objeto Java Product
        return productService.guardarProducto(nuevoProducto);
    }
    
    @PostMapping("/varios")
    public List<Product> crearVarios(@RequestBody List<Product> productos) {
        return productService.guardarVarios(productos);
    }
    
    @GetMapping // Esto le dice a Spring que es para "Pedir" datos
    public List<Product> listarProductos() {
        return productService.obtenerTodos();
    }
    
 // Borrar: DELETE http://localhost:8084/api/productos/(aqui el id del que quieras borrar)
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        productService.borrarPorId(id);
      
    }

    // Actualizar: PUT http://localhost:8084/api/productos/(aqui el id del que quieras actualizar)
    @PutMapping("/{id}")
    public Product actualizar(@PathVariable Long id, @RequestBody Product datosNuevos) {
        // Llamamos al servicio y devolvemos lo que este nos dé 
        return productService.actualizarProducto(id, datosNuevos);
    }
}

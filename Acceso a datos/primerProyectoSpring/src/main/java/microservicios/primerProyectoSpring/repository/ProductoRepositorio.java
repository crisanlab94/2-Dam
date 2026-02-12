package microservicios.primerProyectoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservicios.primerProyectoSpring.modelo.Product;

@Repository
public interface ProductoRepositorio extends JpaRepository<Product, Long> {

}

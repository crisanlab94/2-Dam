package acceso.myshop.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acceso.myshop.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 
    List<Product> findAll();
    Set<Product> findByCategory(String category);
    Product findProductById(long id);

}
  
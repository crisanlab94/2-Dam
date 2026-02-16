package acceso.myshop.services;


import java.util.List;
import java.util.Set;

import acceso.myshop.models.Product;

public interface ProductService {
		List<Product>  findAll();
	    Set<Product> findByCategory(String category);
	    public Product createProduct(Product product) ;
	    public Product findProductById(long id);
	    public Product updateNameProduct (long id,Product product);
	    public void deleteById(Long id);
}

package br.com.ecommerce.services;

import java.util.List;

import br.com.ecommerce.models.Product;

public interface ProductService {
	
	public Product createNewProduct(Product product);
	public Product findById(long id);
	public List<Product> findAll();
	public void deleteProduct(long id);
	
}

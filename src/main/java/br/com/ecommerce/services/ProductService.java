package br.com.ecommerce.services;

import java.util.List;

import br.com.ecommerce.models.Product;

public interface ProductService {
	
	public Product createNewProduct(Product product);
	public List<Product> findAll();
	
}

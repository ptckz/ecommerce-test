package br.com.ecommerce.services.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.models.Product;
import br.com.ecommerce.repositories.ProductRepository;
import br.com.ecommerce.services.ProductService;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product createNewProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(long id) {
		return productRepository.findOne(id);
	}
	
	@Override
	public void deleteProduct(long id) {
		productRepository.delete(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public boolean isProductExist(Product product) {
		return productRepository.findByName(product.getName()) != null;
	}

}

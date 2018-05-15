package br.com.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.models.Product;
import br.com.ecommerce.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Product create(@RequestBody Product input){
        return productService.createNewProduct(input);
    }
    
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> findAll(){
    	return productService.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
    	Product currentProduct = productService.findById(id);
    	
    	currentProduct.setName(product.getName());
    	currentProduct.setValue(product.getValue());
    	
        return productService.updateProduct(currentProduct);
    }
    
	
}

package br.com.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> create(@RequestBody Product input){
    	Product product = productService.createNewProduct(input);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
    
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Product> findAll(){
    	return productService.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
    	Product product = productService.findById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product){
    	Product currentProduct = productService.findById(id);
    	
    	currentProduct.setName(product.getName());
    	currentProduct.setValue(product.getValue());
    	Product productUpdate = productService.updateProduct(currentProduct);
    	
        return new ResponseEntity<Product>(productUpdate, HttpStatus.OK);
    }
	
}

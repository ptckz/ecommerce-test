package br.com.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.models.Product;
import br.com.ecommerce.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
    @PostMapping("/")
    public Product create(@RequestBody Product input){
        return productService.createNewProduct(input);
    }
    
    @ResponseBody
    @GetMapping("/")
    public List<Product> findAll(){
    	return productService.findAll();
    }
	
}

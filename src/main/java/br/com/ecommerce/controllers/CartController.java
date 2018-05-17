package br.com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.dto.AmountDTO;
import br.com.ecommerce.models.Cart;
import br.com.ecommerce.models.Item;
import br.com.ecommerce.models.Product;
import br.com.ecommerce.services.CartService;
import br.com.ecommerce.services.ProductService;
import br.com.ecommerce.util.CustomErrorType;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public  ResponseEntity<?> create(){
		Cart newCart = cartService.createNewCart();
        return new ResponseEntity<Cart>(newCart, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findOne(@PathVariable("id") long id) {
		 Cart currentCart = cartService.findById(id);
		 
		 if (currentCart == null) {
	            return new ResponseEntity(new CustomErrorType("Cart with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	     }
		 return new ResponseEntity<Cart>(currentCart, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/amount/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> returnAmount(@PathVariable("id") long id) {
		 Cart currentCart = cartService.findById(id);
		 
		 if (currentCart == null) {
	            return new ResponseEntity(new CustomErrorType("Cart with id " + id 
	                    + " not found"), HttpStatus.NOT_FOUND);
	     }
		 
		 AmountDTO amountCart = cartService.bindAmountCart(currentCart);
		 return new ResponseEntity<AmountDTO>(amountCart, HttpStatus.OK);
	}

	@RequestMapping(value = "/{idCart}/{idProduto}/{quantity}", method = RequestMethod.GET)
	public ResponseEntity<?> addProductCart(@PathVariable("idCart") long idCart, 
			@PathVariable("idProduto") long idProduto, 
			@PathVariable("quantity") int quantity) {
		
		Cart cart = cartService.findById(idCart);
		Product product = productService.findById(idProduto);
		
		 if (cart == null) 
	         return new ResponseEntity(new CustomErrorType("Cart not found"), HttpStatus.NOT_FOUND);
		 if (product == null)
			 return new ResponseEntity(new CustomErrorType("Product not found"), HttpStatus.NOT_FOUND);
		
		Cart currentCart = cartService.addProductCart(cart, product, quantity);
		
		return new ResponseEntity<Cart>(currentCart, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{idCart}/{idProduto}/{quantity}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuantityProductCart(@PathVariable("idCart") long idCart, 
			@PathVariable("idProduto") long idProduto, 
			@PathVariable("quantity") int quantity) {
		
		Cart cart = cartService.findById(idCart);
		Product product = productService.findById(idProduto);
		
		 if (cart == null) 
	         return new ResponseEntity(new CustomErrorType("Cart not found"), HttpStatus.NOT_FOUND);
		 if (product == null)
			 return new ResponseEntity(new CustomErrorType("Product not found"), HttpStatus.NOT_FOUND);
		
		 Cart currentCart = cartService.updateQuantityProductCart(cart, product, quantity);
		 
		return new ResponseEntity<Cart>(currentCart, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{idCart}/{idProduto}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeProductCart(@PathVariable("idCart") long idCart, 
			@PathVariable("idProduto") long idProduto) {
		
		Cart cart = cartService.findById(idCart);
		Product product = productService.findById(idProduto);
		
		 if (cart == null) 
	         return new ResponseEntity(new CustomErrorType("Cart not found"), HttpStatus.NOT_FOUND);
		 if (product == null)
			 return new ResponseEntity(new CustomErrorType("Product not found"), HttpStatus.NOT_FOUND);
		
		 cartService.removeProductCart(cart, product);
		 
		return new ResponseEntity<Cart>(HttpStatus.NO_CONTENT);
	}
	
}

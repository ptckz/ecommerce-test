package br.com.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.models.Cart;
import br.com.ecommerce.models.Item;
import br.com.ecommerce.models.Product;
import br.com.ecommerce.services.CartService;
import br.com.ecommerce.services.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public Cart create(){
        return cartService.createNewCart();
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Cart findOne(@PathVariable("id") long id) {
		return cartService.findById(id);
	}
	
	@RequestMapping(value = "/{idCart}/{idProduto}/{quantity}", method = RequestMethod.GET)
	public Cart addProductCart(@PathVariable("idCart") long idCart, 
			@PathVariable("idProduto") long idProduto, 
			@PathVariable("quantity") int quantity) {
		
		Cart cart = cartService.findById(idCart);
		Product product = productService.findById(idProduto);
		
		return cartService.addProductCart(cart, product, quantity);
	}
	
	@RequestMapping(value = "/{idCart}/{idProduto}/{quantity}", method = RequestMethod.PUT)
	public Cart updateQuantityProductCart(@PathVariable("idCart") long idCart, 
			@PathVariable("idProduto") long idProduto, 
			@PathVariable("quantity") int quantity) {
		
		Cart cart = cartService.findById(idCart);
		Product product = productService.findById(idProduto);
		
		return cartService.updateQuantityProductCart(cart, product, quantity);
	}
	
	@RequestMapping(value = "/{idCart}/{idProduto}", method = RequestMethod.DELETE)
	public Cart removeProductCart(@PathVariable("idCart") long idCart, 
			@PathVariable("idProduto") long idProduto) {
		
		Cart cart = cartService.findById(idCart);
		Product product = productService.findById(idProduto);
		
		return cartService.removeProductCart(cart, product);
	}
	
}

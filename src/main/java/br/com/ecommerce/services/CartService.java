package br.com.ecommerce.services;

import br.com.ecommerce.models.Cart;
import br.com.ecommerce.models.Product;

public interface CartService {

	public Cart createNewCart();
	public Cart findById(long id);
	public Cart addProductCart(Cart cart, Product product, int quantity);
	public Cart updateQuantityProductCart(Cart cart, Product product, int quantity);
	public void removeProductCart(Cart cart, Product product);
	
}

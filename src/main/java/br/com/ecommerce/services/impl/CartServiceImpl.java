package br.com.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.models.Cart;
import br.com.ecommerce.models.Item;
import br.com.ecommerce.models.Product;
import br.com.ecommerce.repositories.CartRepository;
import br.com.ecommerce.services.CartService;
import br.com.ecommerce.services.ItemService;

@Transactional
@Service("cartService")
public class CartServiceImpl implements CartService{

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	ItemService itemService;
	
	@Override
	public Cart createNewCart() {
		return cartRepository.save(new Cart());
	}

	@Override
	public Cart findById(long id) {
		return cartRepository.findOne(id);
	}
	
	private boolean containsProduct(final List<Item> list, Product product) {
		return list.stream().filter(o -> o.getProduct().equals(product)).findFirst().isPresent();
	}
	
	private double amountCarItem(List<Item> list) {
		double amountCar = 0.0;
		for(Item itemFor : list) {
			amountCar += itemFor.getProduct().getValue() * itemFor.getQuantity();
		}
		return amountCar;
	}

	@Override
	public Cart addProductCart(Cart cart, Product product, int quantity) {
		
		List<Item> list = cart.getItem();
		
		if(containsProduct(list, product)) {
			Optional<Item> item = list.stream().filter(o -> o.getProduct().equals(product)).findFirst();
			Item currentItem = item.get();
			
			currentItem.setQuantity(quantity += currentItem.getQuantity());
			itemService.save(currentItem);
			
			cart.setAmount(amountCarItem(list));
		}else{
			Item item = new Item();
			item.setProduct(product);
			item.setQuantity(quantity);
			
			cart.getItem().add(item);
			
			itemService.save(item);
			
			cart.setAmount(amountCarItem(list));
		}
		
		return cartRepository.save(cart);
	}

	@Override
	public Cart updateQuantityProductCart(Cart cart, Product product, int quantity) {
		List<Item> list = cart.getItem();
		
		Optional<Item> item = list.stream().filter(o -> o.getProduct().equals(product)).findFirst();
		Item currentItem = item.get();
		
		currentItem.setQuantity(currentItem.getQuantity() - quantity);
		itemService.save(currentItem);
		
		cart.setAmount(amountCarItem(list));
		
		return cartRepository.save(cart);
	}

	@Override
	public void removeProductCart(Cart cart, Product product) {
		List<Item> list = cart.getItem();
		
		Optional<Item> item = list.stream().filter(o -> o.getProduct().equals(product)).findFirst();
		Item currentItem = item.get();
		
		list.remove(currentItem);
		itemService.delete(currentItem);
	}
}

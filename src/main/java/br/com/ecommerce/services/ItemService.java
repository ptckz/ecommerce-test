package br.com.ecommerce.services;

import br.com.ecommerce.models.Item;

public interface ItemService {

	public Item save(Item item);
	public void delete(Item item);
	
}

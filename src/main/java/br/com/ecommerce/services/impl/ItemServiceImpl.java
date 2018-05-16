package br.com.ecommerce.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ecommerce.models.Item;
import br.com.ecommerce.repositories.ItemRepository;
import br.com.ecommerce.services.ItemService;

@Transactional
@Service("itemService")
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void delete(Item item) {
		itemRepository.delete(item);
	}

}

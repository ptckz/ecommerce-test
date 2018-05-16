package br.com.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.models.Cart;


public interface CartRepository extends JpaRepository<Cart, Long>{

}

package com.assessment4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment4.entities.CartMerchandise;
import com.assessment4.repositories.CartMerchandiseRepository;

@Service
public class CartMerchandiseService {

	@Autowired
	private CartMerchandiseRepository cartMerchandiseRepository;

	public void saveCartMerchandise(CartMerchandise cartMerchandise) {
		cartMerchandiseRepository.save(cartMerchandise);
	}

	public List<CartMerchandise> findByCartId(int cartId) {
		return cartMerchandiseRepository.findByCartId(cartId);
	}

	public void emptyCart(List<CartMerchandise> list) {
		cartMerchandiseRepository.delete(list);
	}

	public CartMerchandise findByItemId(int id) {
		return cartMerchandiseRepository.findByMerchandiseId(id);
	}

}
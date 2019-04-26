package com.assessment4.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment4.entities.Cart;
import com.assessment4.entities.Merchandise;
import com.assessment4.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;


	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}

	public void addMerch(Merchandise merchandise, Cart cart) {

	}

	public Cart findById(Long id) {
		return cartRepository.findOne(id);
	}
	

	public Cart findByUserEmail(String email) {
		return cartRepository.findByUserEmail(email);
	}

}
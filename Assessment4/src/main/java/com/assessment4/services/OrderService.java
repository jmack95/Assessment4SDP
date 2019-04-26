package com.assessment4.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment4.entities.Cart;
import com.assessment4.entities.Merchandise;
import com.assessment4.entities.UserOrder;
import com.assessment4.repositories.CartRepository;
import com.assessment4.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;


	public void saveOrder(UserOrder order) {
		orderRepository.save(order);
	}

	public void addOrder(UserOrder order, Cart cart) {

	}

	public UserOrder findById(int id) {
		return orderRepository.findOne(id);
	}
	

	public UserOrder findByUserEmail(String email) {
		return orderRepository.findByUserEmail(email);
	}

}
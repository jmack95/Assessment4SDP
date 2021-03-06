package com.assessment4.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class UserOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "USER_EMAIL", updatable = false)
	private User user;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_merchandise", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "merchandise_id"))
	private Set<Merchandise> orderMerchandise;
	
	private double total;
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Merchandise> getMerchandise() {
		return orderMerchandise;
	}

	public void setMerchandise(Set<Merchandise> merchandise) {
		this.orderMerchandise = merchandise;
	}

	
	

	public Set<Merchandise> getOrderMerchandise() {
		return orderMerchandise;
	}

	public void setOrderMerchandise(Set<Merchandise> orderMerchandise) {
		this.orderMerchandise = orderMerchandise;
	}
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	 


	public UserOrder() {
		
	}
	
	 public UserOrder( User user, Set<Merchandise> orderMerchandise,double total) {
	        this.user = user;
	        this.orderMerchandise = orderMerchandise;
	        this.total = total;

	    }

	 public boolean pay(Payment payment, Cart cart) {
	        double totalCost = cart.calculateTotal();
	        return payment.purchase(totalCost);
	    }
	 
	
}
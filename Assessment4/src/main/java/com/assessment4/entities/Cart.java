package com.assessment4.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "USER_EMAIL", nullable = false)
	private User user;
	@OneToMany(mappedBy = "cart")
	private Set<CartMerchandise> cartMerchandise;

	public Set<CartMerchandise> getCartMerchandise() {
		return cartMerchandise;
	}

	public void setCartMerchandise(Set<CartMerchandise> cartMerchandise) {
		this.cartMerchandise = cartMerchandise;
	}

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

	public Cart(User user) {
		this.user = user;
	}

	public Cart() {

	}

	public double calculateTotal() {
		// TODO Auto-generated method stub
		return 0;
	}


}

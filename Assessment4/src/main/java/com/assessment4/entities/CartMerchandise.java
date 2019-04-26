package com.assessment4.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CartMerchandiseID.class)
public class CartMerchandise {
	@Id
	@ManyToOne
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	private Cart cart;
	@Id
	@ManyToOne
	@JoinColumn(name = "merch_id", referencedColumnName = "id")
	private Merchandise merchandise;
	private int amount;

	public CartMerchandise() {

	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Merchandise getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(Merchandise merchandise) {
		this.merchandise = merchandise;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public CartMerchandise(Cart cart, Merchandise merchandise, int amount) {
		this.cart = cart;
		this.merchandise = merchandise;
		this.amount = amount;

	}

}
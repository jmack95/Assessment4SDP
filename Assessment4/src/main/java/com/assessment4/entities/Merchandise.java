package com.assessment4.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Merchandise {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	@Column(unique = true)
	private String title;
	@NotEmpty
	private String manufacturer;
	 private Double price;
	@NotEmpty
	private String category;
	private int quantity;
	
	private String pictureUrl;
	
	@OneToMany(mappedBy="merchandise")
	private Set<CartMerchandise> cartMerchandise;

	
//Getters&setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	


	public Set<CartMerchandise> getCartMerchandise() {
		return cartMerchandise;
	}

	public void setCartMerchandise(Set<CartMerchandise> cartMerchandise) {
		this.cartMerchandise = cartMerchandise;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}




	public Merchandise(String title, String manufacturer, Double price, String category, String pictureUrl) {
		this.title = title;
		this.manufacturer = manufacturer;
		this.price = price;
		this.category = category;
		this.pictureUrl = pictureUrl;
	}
	
	public Merchandise() {
		
		
	}

}

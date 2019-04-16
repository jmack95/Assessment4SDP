package com.assessment4.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Merchandise {

	@Id
	@NotEmpty
	@Column(unique = true)
	private String title;
	@NotEmpty
	private String manufacturer;
	@NotEmpty
	 private Double price;
	@NotEmpty
	private String category;
	
//Getters&setters

	private String pictureUrl;


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

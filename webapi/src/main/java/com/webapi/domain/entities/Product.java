package com.webapi.domain.entities;

import com.webapi.domain.abstractions.BaseEntity;

public class Product extends BaseEntity {
	public String name;
	public String description;
	public int stock;
	public double price;
	public double discount;
	public String[] productImages;
	public int retailerId;
	
	public Retailer retailer;
}

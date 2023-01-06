package com.webapi.domain.entities;

import com.webapi.domain.abstractions.BaseEntity;

public class OrderDetail extends BaseEntity {
	
	public int orderId;
	public int productId;
	public int quantity;
	public double discount;
	
	public Order order;
	public Product product;
}

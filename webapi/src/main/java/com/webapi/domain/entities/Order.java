package com.webapi.domain.entities;

import com.webapi.domain.abstractions.BaseEntity;
import java.time.LocalDateTime;

public class Order extends BaseEntity {
	public int supplierId;
	public LocalDateTime createdTime;
	
	public Supplier supplier;
	public OrderDetail[] orderDetails;
}

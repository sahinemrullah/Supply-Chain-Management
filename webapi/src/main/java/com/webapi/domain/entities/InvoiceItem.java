package com.webapi.domain.entities;

import com.webapi.domain.abstractions.BaseEntity;

public class InvoiceItem extends BaseEntity {
	public int orderDetailId;
	public OrderDetail orderDetail;
}

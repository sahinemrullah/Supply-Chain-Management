package com.webapi.domain.entities;

import com.webapi.domain.abstractions.BaseEntity;
import java.time.LocalDateTime;

public class Invoice extends BaseEntity {
	public int supplierId;
	public int retailerId;
	public LocalDateTime date;

	public InvoiceItem[] items;
	public Supplier supplier;
	public Retailer retailer;
}

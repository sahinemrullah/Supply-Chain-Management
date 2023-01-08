package com.webapi.persistence.abstractions;

import com.webapi.domain.entities.Retailer;

public interface IRetailerRepository extends IRepository<Retailer> {
	public boolean retailerExists(String email);

	public Retailer getRetailer(String email); 
}

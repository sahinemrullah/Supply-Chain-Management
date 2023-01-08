package com.webapi.persistence.abstractions;

import com.webapi.domain.entities.Supplier;

public interface ISupplierRepository extends IRepository<Supplier> {
	public boolean supplierExists(String email);

	public Supplier getSupplier(String email); 
}

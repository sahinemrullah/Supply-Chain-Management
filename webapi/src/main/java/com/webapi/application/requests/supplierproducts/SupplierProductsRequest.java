
package com.webapi.application.requests.supplierproducts;

import com.webapi.application.concretes.PaginatedRequest;

public class SupplierProductsRequest extends PaginatedRequest {
    private int supplierId;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}

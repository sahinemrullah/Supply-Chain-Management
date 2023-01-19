
package com.webapi.application.requests.outofstockproducts;

import com.webapi.application.concretes.PaginatedRequest;

public class OutOfProductsRequest extends PaginatedRequest {
    private int supplierId;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
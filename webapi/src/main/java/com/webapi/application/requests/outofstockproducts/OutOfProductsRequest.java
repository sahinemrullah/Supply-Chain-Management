
package com.webapi.application.requests.outofstockproducts;

import com.webapi.application.concretes.PaginatedRequest;

public class OutOfProductsRequest extends PaginatedRequest {
    private int retailerId;

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
}

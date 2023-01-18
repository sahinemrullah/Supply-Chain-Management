
package com.webapi.application.requests.retailerproducts;

import com.webapi.application.concretes.PaginatedRequest;

public class RetailerProductsRequest extends PaginatedRequest {
    private int retailerId;

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
}

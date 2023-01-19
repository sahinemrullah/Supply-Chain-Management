
package com.webapi.application.requests.orderhistory;

import com.webapi.application.concretes.PaginatedRequest;

public class OrderHistoryRequest extends PaginatedRequest {
    private int retailerId;

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
}

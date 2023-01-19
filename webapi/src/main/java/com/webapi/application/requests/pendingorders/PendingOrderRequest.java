
package com.webapi.application.requests.pendingorders;

import com.webapi.application.concretes.PaginatedRequest;

public class PendingOrderRequest extends PaginatedRequest {
    private int retailerId;

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
}

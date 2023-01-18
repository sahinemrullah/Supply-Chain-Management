
package com.webapi.application.requests.orderhistory;

import com.webapi.application.concretes.PaginatedRequest;

public class OrderHistoryRequest extends PaginatedRequest {
    private int supplierId;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}

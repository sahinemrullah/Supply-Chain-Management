
package com.webapi.application.requests.pendingorders;

import com.webapi.application.concretes.PaginatedRequest;

public class PendingOrderRequest extends PaginatedRequest {
    private int supplierId;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}

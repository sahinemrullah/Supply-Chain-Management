
package com.webapi.application.requests.invoicelist;

import com.webapi.application.concretes.PaginatedRequest;

public class InvoiceListRequest extends PaginatedRequest {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

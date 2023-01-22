
package com.webapi.application.requests.invoicelist;

import com.webapi.application.concretes.PaginatedRequest;

public class InvoiceListRequest extends PaginatedRequest {
    private int userId;
    private String role;
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

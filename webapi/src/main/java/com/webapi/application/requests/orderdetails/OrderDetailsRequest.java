
package com.webapi.application.requests.orderdetails;

import com.webapi.application.abstractions.IRequest;

public class OrderDetailsRequest implements IRequest {
    private int id;
    private int userId;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

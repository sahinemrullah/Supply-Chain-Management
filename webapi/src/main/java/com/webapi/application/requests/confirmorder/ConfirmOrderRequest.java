package com.webapi.application.requests.confirmorder;

import com.webapi.application.abstractions.IRequest;

public class ConfirmOrderRequest implements IRequest {
    private int id;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

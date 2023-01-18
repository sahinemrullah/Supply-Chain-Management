package com.webapi.application.requests.editstock;

import com.webapi.application.abstractions.IRequest;

public class EditStockRequest implements IRequest {
    private int id;
    private int stock;
    private int userId;
    private boolean isRetailer;

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

    public boolean getIsRetailer() {
        return isRetailer;
    }

    public void setIsRetailer(boolean isRetailer) {
        this.isRetailer = isRetailer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

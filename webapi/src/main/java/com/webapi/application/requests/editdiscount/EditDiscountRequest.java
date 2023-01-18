package com.webapi.application.requests.editdiscount;

import com.webapi.application.abstractions.IRequest;

public class EditDiscountRequest implements IRequest {
    private int id;
    private int discount;
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}

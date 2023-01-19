package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.IRequest;
import java.util.Map;

public class CreateOrderRequest implements IRequest {
    private int retailerId;
    private Map<Integer, Integer> products;

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}

package com.webapi.application.models.order;

import java.util.Map;

public class CreateOrderModel {
    private Map<Integer, Integer> products;

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}

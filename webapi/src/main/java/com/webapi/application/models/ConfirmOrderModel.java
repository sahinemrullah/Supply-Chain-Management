package com.webapi.application.models;

import java.util.Map;

public class ConfirmOrderModel {
    private Map<Integer, Integer> products;

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}

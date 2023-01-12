package com.webapp.models;

import java.util.Map;
import java.util.stream.Collectors;

public class ConfirmOrderModel {
    private Map<Integer, Integer> products;
    
    public ConfirmOrderModel() {
        
    }
    
    public ConfirmOrderModel(Cart cart) {
        products = cart.getItems().stream().collect(Collectors.toMap(CartItem::getId, CartItem::getQuantity));
    }
    
    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}
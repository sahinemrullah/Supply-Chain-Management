package com.webapp.models.cart;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items;
    
    public Cart() {
        items = new ArrayList<>();
    }
    
    public ArrayList<CartItem> getItems() {
        return items;
    }

    public boolean addItem(CartItem item) {
        return items.add(item);
    }
}

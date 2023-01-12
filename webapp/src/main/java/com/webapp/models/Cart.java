package com.webapp.models;

import java.util.ArrayList;

public class Cart<T> {
    private ArrayList<T> items;
    
    public Cart() {
        items = new ArrayList<>();
    }
    
    public ArrayList<T> getItems() {
        return items;
    }

    public boolean addItem(T item) {
        return items.add(item);
    }
}

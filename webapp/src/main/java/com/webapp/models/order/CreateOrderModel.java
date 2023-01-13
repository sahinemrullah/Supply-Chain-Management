package com.webapp.models.order;

import com.webapp.models.cart.CartItem;
import com.webapp.models.cart.Cart;
import java.util.Map;
import java.util.stream.Collectors;

public class CreateOrderModel {
    private Map<Integer, Integer> products;
    
    public CreateOrderModel() {
        
    }
    
    public CreateOrderModel(Cart cart) {
        products = cart.getItems().stream().collect(Collectors.toMap(CartItem::getId, CartItem::getQuantity));
    }
    
    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}

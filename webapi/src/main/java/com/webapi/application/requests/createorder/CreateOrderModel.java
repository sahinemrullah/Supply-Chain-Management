package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.IRequest;
import java.util.Map;

public class CreateOrderModel implements IRequest {
    private int supplierId;
    private Map<Integer, Integer> products;

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Integer, Integer> products) {
        this.products = products;
    }
}

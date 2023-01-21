package com.webapi.application.requests.orderdetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailsModel {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date createdDate;
    private List<OrderDetailsListModel> products;

    public OrderDetailsModel() {
        products = new ArrayList<>();
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<OrderDetailsListModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderDetailsListModel> products) {
        this.products = products;
    }
    
    public boolean addProduct(OrderDetailsListModel product) {
        return products.add(product);
    }
}

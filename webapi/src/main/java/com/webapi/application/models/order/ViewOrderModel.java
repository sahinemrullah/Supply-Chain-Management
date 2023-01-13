package com.webapi.application.models.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewOrderModel {
    private Date createdDate;
    private List<ViewOrderListModel> products;

    public ViewOrderModel() {
        products = new ArrayList<>();
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ViewOrderListModel> getProducts() {
        return products;
    }

    public void setProducts(List<ViewOrderListModel> products) {
        this.products = products;
    }
    
    public boolean addProduct(ViewOrderListModel product) {
        return products.add(product);
    }
}

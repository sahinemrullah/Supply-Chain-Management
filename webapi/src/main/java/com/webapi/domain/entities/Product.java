package com.webapi.domain.entities;

import com.webapi.domain.abstractions.BaseEntity;
import java.util.ArrayList;
import java.util.List;

public class Product extends BaseEntity {

    private String name;
    private String description;
    private int stock;
    private double price;
    private double discount;
    private List<String> productImages;
    private int retailerId;

    public Product() {
        productImages = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(ArrayList<String> productImages) {
        this.productImages = productImages;
    }

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
    
    public void addImage(String path) {
        this.productImages.add(path);
    }
}

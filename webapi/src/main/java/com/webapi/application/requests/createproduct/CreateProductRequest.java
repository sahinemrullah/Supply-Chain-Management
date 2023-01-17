package com.webapi.application.requests.createproduct;

import com.webapi.application.abstractions.IRequest;
import java.util.ArrayList;

public class CreateProductRequest implements IRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
    private ArrayList<String> productImages;
    private int retailerId;

    public int getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }
    
    public CreateProductRequest() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(ArrayList<String> productImages) {
        this.productImages = productImages;
    }    
}

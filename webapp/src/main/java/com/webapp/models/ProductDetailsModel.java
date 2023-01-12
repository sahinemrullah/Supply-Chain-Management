/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.models;

import java.util.ArrayList;

public class ProductDetailsModel {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private int retailerName;
    private ArrayList<String> productImages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(int retailerName) {
        this.retailerName = retailerName;
    }
    
    public ProductDetailsModel() {
        productImages = new ArrayList<String>();
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

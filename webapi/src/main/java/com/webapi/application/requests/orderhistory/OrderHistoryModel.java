package com.webapi.application.requests.orderhistory;

import java.util.Date;

public class OrderHistoryModel {
    private int id;
    private String supplierName;
    private Date createdDate;
    private double total;
    private boolean isPending;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isIsPending() {
        return isPending;
    }

    public void setIsPending(boolean isPending) {
        this.isPending = isPending;
    }
}

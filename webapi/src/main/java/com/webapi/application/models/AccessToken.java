
package com.webapi.application.models;

public class AccessToken {
    private int userId;
    private String token;
    private boolean isRetailer;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIsRetailer() {
        return isRetailer;
    }

    public void setIsRetailer(boolean isRetailer) {
        this.isRetailer = isRetailer;
    }
}

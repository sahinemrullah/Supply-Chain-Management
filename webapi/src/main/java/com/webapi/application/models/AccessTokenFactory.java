
package com.webapi.application.models;

import com.webapi.application.utils.EncryptionUtils;

public class AccessTokenFactory {
    private int userId;
    private String passwordHash;
    private String password;
    private String role;

    public AccessToken build() {
        if(EncryptionUtils.checkHash(password, passwordHash)) {
            AccessToken accessToken = new AccessToken();
        
            accessToken.setRole(role);
            accessToken.setUserId(userId);
            accessToken.setToken(EncryptionUtils.createJWT(userId, role, "localhost:9080", "localhost:8080"));

            return accessToken;
        }
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

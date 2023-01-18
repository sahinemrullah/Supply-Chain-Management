package com.webapi.application.requests.supplierlogin;

import com.webapi.application.abstractions.IRequest;

public class SupplierLoginRequest implements IRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


package com.webapi.application.requests.productdetails;

import com.webapi.application.abstractions.IRequest;

public class ProductDetailsRequest implements IRequest {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


package com.webapi.application.requests.productsearch;

import com.webapi.application.concretes.PaginatedRequest;

public class ProductSearchRequest extends PaginatedRequest {
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}

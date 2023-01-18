
package com.webapi.application.concretes;

import com.webapi.application.abstractions.IRequest;

public class PaginatedRequest implements IRequest {
    private int pageNumber;
    private int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

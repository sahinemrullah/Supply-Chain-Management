package com.webapi.application.servlets;

import com.webapi.application.concretes.PaginatedRequest;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.models.PaginatedListModel;
import jakarta.servlet.http.HttpServletRequest;
import java.util.function.Supplier;

public abstract class PaginatedServlet<T extends PaginatedRequest, U extends Object> extends BaseServlet<T, PaginatedListModel<U>> {

    protected T parsePagedRequest(HttpServletRequest httpRequest, Supplier<T> constructor) throws ModelValidationException {
        T request = constructor.get();

        String pageNumberStr = httpRequest.getParameter("pageNumber");

        Integer pageNumber = tryParseInt(pageNumberStr);

        if (pageNumber == null) {
            pageNumber = 1;
        }

        String pageSizeStr = httpRequest.getParameter("pageSize");
        
        Integer pageSize = tryParseInt(pageSizeStr);

        if (pageSize == null) {
            pageSize = 10;
        }
        
        request.setPageNumber(pageNumber);
        request.setPageSize(pageSize);
        
        return request;
    }
}

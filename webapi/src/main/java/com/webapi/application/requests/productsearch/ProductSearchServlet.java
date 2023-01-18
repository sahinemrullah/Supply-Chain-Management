package com.webapi.application.requests.productsearch;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.servlets.PaginatedServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductSearchServlet", urlPatterns = {"/product/search"})
public class ProductSearchServlet extends PaginatedServlet<ProductSearchRequest, ProductSearchModel> {

    @Override
    protected ProductSearchRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        String query = httpRequest.getParameter("query");
        
        if(query == null)
            throw new ModelValidationException("Query boş olamaz.");
        
        ProductSearchRequest request = parsePagedRequest(httpRequest, ProductSearchRequest::new);
        
        request.setQuery(query);
        
        request.setIsRetailer(getIsRetailer(httpRequest));
        
        return request;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<ProductSearchRequest, PaginatedListModel<ProductSearchModel>> getRequestHandler() {
        return new ProductSearchRequestHandler();
    }

}

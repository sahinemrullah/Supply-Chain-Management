package com.webapi.application.requests.orderhistory;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.servlets.PaginatedServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/supplier/orders"})
public class OrderHistoryServlet extends PaginatedServlet<OrderHistoryRequest, OrderHistoryModel> {
    
    @Override
    protected OrderHistoryRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        OrderHistoryRequest request = parsePagedRequest(httpRequest, OrderHistoryRequest::new);
        
        request.setSupplierId(getUserId(httpRequest));
        
        return request;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<OrderHistoryRequest, PaginatedListModel<OrderHistoryModel>> getRequestHandler() {
        return new OrderHistoryRequestHandler();
    }
}


package com.webapi.application.requests.createorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;
import com.webapi.application.utils.HttpServletRequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/order/create"})
public class CreateOrderServlet extends BaseServlet<CreateOrderModel, Void> {

    @Override
    protected CreateOrderModel parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        CreateOrderModel request = getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), CreateOrderModel.class);
        
        request.setSupplierId(getUserId(httpRequest));
        
        return request;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<CreateOrderModel, Void> getRequestHandler() {
        return new CreateOrderRequestHandler();
    }

}

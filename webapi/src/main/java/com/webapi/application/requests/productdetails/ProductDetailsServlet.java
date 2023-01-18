package com.webapi.application.requests.productdetails;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/get")
public class ProductDetailsServlet extends BaseServlet<ProductDetailsRequest, ProductDetailsModel> {

    @Override
    protected ProductDetailsRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        String idStr = httpRequest.getParameter("id");
        
        Integer id = tryParseInt(idStr);
        
        if(id == null)
            throw new ModelValidationException("Id boş olamaz.");
        
        ProductDetailsRequest request = new ProductDetailsRequest();
        request.setId(id);
        
        return request;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<ProductDetailsRequest, ProductDetailsModel> getRequestHandler() {
        return new ProductDetailsRequestHandler();
    }

}

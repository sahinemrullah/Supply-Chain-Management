package com.webapi.application.requests.createproduct;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;
import com.webapi.application.utils.HttpServletRequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateProductServlet", urlPatterns = {"/product/create"})
public class CreateProductServlet extends BaseServlet<CreateProductRequest, Void> {

    @Override
    protected CreateProductRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        CreateProductRequest request = getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), CreateProductRequest.class);

        Integer userId = getUserId(httpRequest);

        if (userId == null) {
            throw new ModelValidationException("Invalid user.");
        }

        request.setRetailerId(userId);

        return request;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<CreateProductRequest, Void> getRequestHandler() {
        return new CreateProductRequestHandler();
    }

}

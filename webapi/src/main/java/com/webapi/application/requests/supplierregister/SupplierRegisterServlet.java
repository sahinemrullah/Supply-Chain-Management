package com.webapi.application.requests.supplierregister;

import java.io.IOException;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.utils.HttpServletRequestUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;

@WebServlet("/supplier/register")
public class SupplierRegisterServlet extends BaseServlet<SupplierRegisterRequest, Void> {

    @Override
    protected SupplierRegisterRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        return getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), SupplierRegisterRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<SupplierRegisterRequest, Void> getRequestHandler() {
        return new SupplierRegisterRequestHandler();
    }

}

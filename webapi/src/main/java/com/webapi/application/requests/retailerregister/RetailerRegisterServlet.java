package com.webapi.application.requests.retailerregister;

import java.io.IOException;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.utils.HttpServletRequestUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;

@WebServlet("/retailer/register")
public class RetailerRegisterServlet extends BaseServlet<RetailerRegisterRequest, Void> {

    @Override
    protected RetailerRegisterRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        return getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), RetailerRegisterRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<RetailerRegisterRequest, Void> getRequestHandler() {
        return new RetailerRegisterRequestHandler();
    }

}

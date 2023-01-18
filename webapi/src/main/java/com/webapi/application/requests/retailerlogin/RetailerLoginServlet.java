package com.webapi.application.requests.retailerlogin;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.models.AccessToken;
import com.webapi.application.servlets.BaseServlet;
import com.webapi.application.utils.HttpServletRequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RetailerLoginServlet", urlPatterns = {"/retailer/login"})
public class RetailerLoginServlet extends BaseServlet<RetailerLoginRequest, AccessToken> {

    @Override
    protected RetailerLoginRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        return getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), RetailerLoginRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<RetailerLoginRequest, AccessToken> getRequestHandler() {
        return new RetailerLoginRequestHandler();
    }

}

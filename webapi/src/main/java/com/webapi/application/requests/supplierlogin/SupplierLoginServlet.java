package com.webapi.application.requests.supplierlogin;

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

@WebServlet(name = "SupplierLoginServlet", urlPatterns = {"/supplier/login"})
public class SupplierLoginServlet extends BaseServlet<SupplierLoginRequest, AccessToken> {

    @Override
    protected SupplierLoginRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        return getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), SupplierLoginRequest.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<SupplierLoginRequest, AccessToken> getRequestHandler() {
        return new SupplierLoginRequestHandler();
    }

}

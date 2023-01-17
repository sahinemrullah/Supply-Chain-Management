package com.webapi.application.requests.confirmorder;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;
import com.webapi.application.utils.HttpServletRequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmOrderServlet", urlPatterns = {"/order/confirm"})
public class ConfirmOrderServlet extends BaseServlet<ConfirmOrderRequest, Void> {

    @Override
    protected ConfirmOrderRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        ConfirmOrderRequest request = getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), ConfirmOrderRequest.class);

        Integer userId = getUserId(httpRequest);

        if (userId == null) {
            throw new ModelValidationException("Invalid user.");
        }

        request.setUserId(userId);

        return request;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<ConfirmOrderRequest, Void> getRequestHandler() {
        return new ConfirmOrderRequestHandler();
    }

}

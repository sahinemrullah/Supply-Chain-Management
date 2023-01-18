package com.webapi.application.requests.editstock;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;
import com.webapi.application.utils.HttpServletRequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditStockServlet", urlPatterns = {"/product/editStock"})
public class EditStockServlet extends BaseServlet<EditStockRequest, Void> {

    @Override
    protected EditStockRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        EditStockRequest request = getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), EditStockRequest.class);
        
        request.setIsRetailer(getIsRetailer(httpRequest));
        request.setUserId(getUserId(httpRequest));
        
        return request;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<EditStockRequest, Void> getRequestHandler() {
        return new EditStockRequestHandler();
    }

}

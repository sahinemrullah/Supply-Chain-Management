package com.webapi.application.requests.editdiscount;

import com.webapi.application.abstractions.IRequestHandler;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.servlets.BaseServlet;
import com.webapi.application.utils.HttpServletRequestUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditDiscountServlet", urlPatterns = {"/product/editDiscount"})
public class EditDiscountServlet extends BaseServlet<EditDiscountRequest, Void> {

    @Override
    protected EditDiscountRequest parseRequest(HttpServletRequest httpRequest) throws ModelValidationException {
        EditDiscountRequest request = getJsonParser().fromJson(HttpServletRequestUtils.getBody(httpRequest), EditDiscountRequest.class);
        
        request.setIsRetailer(getIsRetailer(httpRequest));
        request.setUserId(getUserId(httpRequest));
        
        return request;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected IRequestHandler<EditDiscountRequest, Void> getRequestHandler() {
        return new EditDiscountRequestHandler();
    }

}

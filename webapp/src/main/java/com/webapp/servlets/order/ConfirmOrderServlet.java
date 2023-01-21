package com.webapp.servlets.order;

import com.webapp.models.order.ConfirmOrderModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ConfirmOrderServlet", urlPatterns = {"/order/confirm"})
public class ConfirmOrderServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String orderIdStr = request.getParameter("orderId");

        int orderId = Integer.parseInt(orderIdStr);

        ConfirmOrderModel model = new ConfirmOrderModel();
        model.setId(orderId);
        
        StringBuilder builder = new StringBuilder("");
        
        builder.append("suppliers/")
                .append(getUserId(session))
                .append("/orders/")
                .append(orderIdStr);
        
        Result result = RequestBuilder.create()
                .withURL(builder.toString())
                .withToken(getToken(session))
                .post(model);
        
        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/satici/");
    }
    
    @Override
    protected String getJSPPage() {
        return "";
    }
}

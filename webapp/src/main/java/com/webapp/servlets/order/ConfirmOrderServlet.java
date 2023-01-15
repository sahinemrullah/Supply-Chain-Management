package com.webapp.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.order.ConfirmOrderModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ConfirmOrderServlet", urlPatterns = {"/order/confirm"})
public class ConfirmOrderServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isRetailer = (boolean) session.getAttribute("isRetailer");
        
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        
        if(isRetailer) {
            String token = (String) session.getAttribute("token");
            ConfirmOrderModel model = new ConfirmOrderModel();
            model.setId(orderId);
            Response result = HttpRequestUtils.post("http://localhost:9080/order/confirm", model, token);
            if(result.getStatusCode() == 200) {
                response.sendRedirect("/tedarikci/");
            } else {
                response.getWriter().write(result.getResponseMessage());
            }
        } else {
            response.setStatus(403);
        }
    }
}

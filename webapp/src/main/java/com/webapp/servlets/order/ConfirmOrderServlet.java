package com.webapp.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.cart.Cart;
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
        if(!isRetailer) {
            Cart cart = (Cart)session.getAttribute("cart");
            if(cart == null || cart.getItems().isEmpty())
                response.sendRedirect("/sepet");
            String token = (String) session.getAttribute("token");
            Response result = HttpRequestUtils.post("http://localhost:9080/order/confirm", new ConfirmOrderModel(cart), token);
            if(result.getStatusCode() == 200) {
                response.sendRedirect("/satici/siparislerim");
            } else {
                response.getWriter().write(result.getResponseMessage());
            }
        } else {
            response.setStatus(403);
        }
    }
}

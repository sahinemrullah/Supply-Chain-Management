package com.webapp.servlets.cart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.cart.Cart;
import com.webapp.models.cart.CartItem;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ViewCartServlet", urlPatterns = {"/sepet"})
public class ViewCartServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean isRetailer = (boolean) session.getAttribute("isRetailer");
        if(!isRetailer) {
            Cart cart = (Cart)session.getAttribute("cart");
            if(cart == null) {
                cart = new Cart();
            }
            request.setAttribute("cart", cart);
            double priceSum = 0.0d;
            for(CartItem item : cart.getItems()) {
                priceSum += item.getPrice();
            }
            request.setAttribute("priceSum", priceSum);
            request.getRequestDispatcher("/WEB-INF/cart/cartDetails.jsp").forward(request, response);
        }
    }
}

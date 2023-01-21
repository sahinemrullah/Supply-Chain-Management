package com.webapp.servlets.cart;

import com.webapp.models.cart.Cart;
import com.webapp.models.cart.CartItem;
import com.webapp.servlets.BaseServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewCartServlet", urlPatterns = {"/sepet"})
public class ViewCartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cart cart = getCart(request);
        
        double priceSum = 0.0d;
        for (CartItem item : cart.getItems()) {
            priceSum += item.getPrice() * (1.0d - item.getDiscount()) * item.getQuantity();
        }
        request.setAttribute("priceSum", priceSum);
        request.getRequestDispatcher(getJSPPage()).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = getCart(request);
        
        CartItem existingItem = cart.getItems()
                .stream()
                .filter(itm -> itm.getId() == Integer.parseInt(request.getParameter("id")))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            cart.removeItem(existingItem);
        }
        
        response.sendRedirect(getReferer(request));
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/cart/cartDetails.jsp";
    }
}

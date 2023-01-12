/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.webapp.servlets.cart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.Cart;
import com.webapp.models.CartItem;
import com.webapp.models.ProductDetailsModel;
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

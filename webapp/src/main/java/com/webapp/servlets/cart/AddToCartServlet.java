/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.webapp.servlets.cart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.Cart;
import com.webapp.models.ProductDetailsModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.IntegerUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/cart/add"})
public class AddToCartServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        
        if(idStr == null || !IntegerUtils.tryParse(idStr))
            response.sendRedirect("/");
        
        String token = (String)request.getSession().getAttribute("token");
        
        HashMap<String, String> parameters = new HashMap<String, String>();
        
        parameters.put("id", idStr);
        
        Response result = HttpRequestUtils.get("http://localhost:9080/product/get", parameters, token);
        
        if(result.getStatusCode() == 200) {
            ProductDetailsModel item = GSON.fromJson(result.getResponseMessage(), ProductDetailsModel.class);
            
            HttpSession session = request.getSession();
            
            Cart<ProductDetailsModel> cart = (Cart<ProductDetailsModel>) session.getAttribute("cart");
            
            if(cart == null)
            {
                cart = new Cart<>(); 
                session.setAttribute("cart", cart);
            }
                
            
            ProductDetailsModel existingItem = cart.getItems()
                                                    .stream()
                                                    .filter(itm -> itm.getId() == Integer.parseInt(idStr))
                                                    .findFirst()
                                                    .orElse(null);
            
            if(existingItem == null)
            {
                cart.addItem(item);
                response.sendRedirect("/cart");
            }
            else {
                response.setStatus(400);
                response.getWriter().write("Bu ürün sepetinizde bulunmaktadır.");
            }
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
package com.webapp.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.Cart;
import com.webapp.models.ConfirmOrderModel;
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

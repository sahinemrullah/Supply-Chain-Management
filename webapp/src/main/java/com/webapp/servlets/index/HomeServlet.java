package com.webapp.servlets.index;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "HomeServlet", urlPatterns = {"/index"})
public class HomeServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            boolean isRetailer = (boolean) session.getAttribute("isRetailer");
            if(isRetailer)
                request.getRequestDispatcher("/WEB-INF/retailer/home.jsp").forward(request, response);
            else
                request.getRequestDispatcher("/WEB-INF/supplier/home.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/index/login.jsp").forward(request, response);
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
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.webapp.servlets.supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.LoginModel;
import com.webapp.utils.HttpRequestUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SupplierLoginServlet", urlPatterns = {"/satici/giris"})
public class SupplierLoginServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/supplier/login.jsp").forward(request, response);
    }

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
        LoginModel model = new LoginModel();
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));

        String result = HttpRequestUtils.post("http://localhost:9080/supplier/login", model);

        if (result.length() == 0) {
            request.setAttribute("emailError", "Geçersiz kullanıcı bilgileri girdiniz.");

            request.getRequestDispatcher("/WEB-INF/supplier/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("token", result);
            request.getRequestDispatcher("/WEB-INF/supplier/dashboard.jsp").forward(request, response);
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
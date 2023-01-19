package com.webapp.servlets.supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.LoginModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.JWTUtils;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SupplierLoginServlet", urlPatterns = {"/satici/giris"})
public class SupplierLoginServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/supplier/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginModel model = new LoginModel();
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));

        Result result = HttpRequestUtils.post("http://localhost:9080/supplier/login", model);

        if (result.getStatusCode() == 400) {
            request.setAttribute("emailError", "Geçersiz kullanıcı bilgileri girdiniz.");
            request.setAttribute("email", model.getEmail());
            
            request.getRequestDispatcher("/WEB-INF/supplier/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("token", result.getResponseMessage());
            JWTUtils.parseToken(session);
            response.sendRedirect("/satici/");
        }
    }
}

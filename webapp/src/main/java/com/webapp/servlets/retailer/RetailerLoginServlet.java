package com.webapp.servlets.retailer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.LoginModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.JWTUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RetailerLoginServlet", urlPatterns = {"/tedarikci/giris"})
public class RetailerLoginServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/retailer/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginModel model = new LoginModel();
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));

        Response result = HttpRequestUtils.post("http://localhost:9080/retailer/login", model);

        if (result.getStatusCode() == 400) {
            request.setAttribute("emailError", "Geçersiz kullanıcı bilgileri girdiniz.");
            request.setAttribute("email", model.getEmail());

            request.getRequestDispatcher("/WEB-INF/retailer/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("token", result.getResponseMessage());
            JWTUtils.parseToken(session);
            response.sendRedirect("/tedarikci/");
        }
    }
}

package com.webapp.servlets.supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.RegisterModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Map.Entry;

@WebServlet(name = "SupplierSignUpServlet", urlPatterns = {"/satici/kayitol"})
public class SupplierRegisterServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/supplier/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterModel model = new RegisterModel();
        model.setName(request.getParameter("name"));
        model.setPhoneNumber(request.getParameter("phoneNumber"));
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));
        model.setPasswordVerification(request.getParameter("passwordVerification"));

        Response result = HttpRequestUtils.post("http://localhost:9080/supplier/register", model);

        if (result.getStatusCode() == 400) {
            Map<String, String[]> map = GSON.fromJson(result.getResponseMessage(), Map.class);

            for (Entry<String, String[]> entry : map.entrySet()) {
                request.setAttribute(entry.getKey() + "Error", entry.getValue());
            }

            request.setAttribute("name", model.getName());
            request.setAttribute("phoneNumber", model.getPhoneNumber());
            request.setAttribute("email", model.getEmail());

            request.getRequestDispatcher("/WEB-INF/supplier/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/supplier/login.jsp").forward(request, response);
        }
    }

}

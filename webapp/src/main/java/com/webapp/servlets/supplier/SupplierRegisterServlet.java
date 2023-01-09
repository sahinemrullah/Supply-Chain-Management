/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.webapp.servlets.supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.webapp.models.RegisterModel;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author LütfullahŞAHİN
 */
@WebServlet(name = "SupplierSignUpServlet", urlPatterns = {"/satici/kayitol"})
public class SupplierRegisterServlet extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/supplier/register.jsp").forward(request, response);
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
        URL url = new URL ("http://localhost:9080/supplier/register");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        RegisterModel model = new RegisterModel();
        model.setName(request.getParameter("name"));
        model.setPhoneNumber(request.getParameter("phoneNumber"));
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));
        model.setPasswordVerification(request.getParameter("passwordVerification"));
        Gson GSON = new GsonBuilder().create();
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = GSON.toJson(model).getBytes("utf-8");
            os.write(input, 0, input.length);			
        }
        int statusCode = con.getResponseCode();
        if (statusCode >= 200 && statusCode < 400) {
            request.getRequestDispatcher("/WEB-INF/supplier/login.jsp").forward(request, response);
        }
        else {
           try(BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getErrorStream(), "utf-8"))) {
              StringBuilder serverResponse = new StringBuilder();
              String responseLine = null;
              while ((responseLine = br.readLine()) != null) {
                  serverResponse.append(responseLine.trim());
              }
              
              Type stringStringArrMap = new TypeToken<Map<String, String[]>>(){}.getType();
              Map<String, String[]> map = GSON.fromJson(serverResponse.toString(), Map.class);
             
              for(Entry<String, String[]> entry : map.entrySet()) {
                  request.setAttribute(entry.getKey() + "Error", entry.getValue());
              }
              
              request.setAttribute("name", model.getName());
              request.setAttribute("phoneNumber", model.getPhoneNumber());
              request.setAttribute("email", model.getEmail());
              
              request.getRequestDispatcher("/WEB-INF/supplier/register.jsp").forward(request, response);
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
    }

}

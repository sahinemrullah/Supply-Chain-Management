/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.application.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.domain.entities.Product;
import com.webapi.persistence.abstractions.IProductRepository;
import com.webapi.persistence.concretes.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product/get")
public class ProductDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private IProductRepository productRepository;

    public ProductDetailsServlet() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        
        if(idStr == null)
            response.setStatus(400);

        response.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);

        Product product = null;
        try {
            product = productRepository.findById(Integer.parseInt(idStr));
            if (product != null) {
                response.getWriter().write(GSON.toJson(product));
            } else {
                response.setStatus(404);
            }
        } catch (SQLException ex) {
            response.setStatus(500);
        }

    }

}

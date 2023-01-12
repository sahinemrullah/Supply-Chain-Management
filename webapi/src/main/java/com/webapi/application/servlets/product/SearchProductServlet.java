/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.application.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.ProductSearchModel;
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
import java.util.List;

@WebServlet("/product/search")
public class SearchProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private IProductRepository productRepository;

    public SearchProductServlet() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        String pageNumberStr = request.getParameter("pageNumber");
        String pageSizeStr = request.getParameter("pageSize");
        
        if(query == null || pageNumberStr == null || pageSizeStr == null)
            response.setStatus(400);
        
        int pageNumber = Integer.parseInt(pageNumberStr);
        int pageSize = Integer.parseInt(pageSizeStr);
        
        response.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);
        
        try {
            PaginatedListModel<ProductSearchModel> model = productRepository.paginatedSearch(query, pageNumber, pageSize);
            
            response.getWriter().write(GSON.toJson(model));
        } catch (SQLException ex) {
            response.getWriter().write(ex.getMessage());
            
            response.setStatus(500);
        }

    }

}
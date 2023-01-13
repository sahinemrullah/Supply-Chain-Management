package com.webapi.application.servlets.retailer;

import com.webapi.application.models.retailer.ProductListModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.persistence.abstractions.IProductRepository;
import com.webapi.persistence.concretes.ProductRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(name = "ProductListServlet", urlPatterns = {"/retailer/myProducts"})
public class ProductListServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    private final IProductRepository productRepository;
    
    public ProductListServlet() {
        productRepository = new ProductRepository();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageNumberStr = request.getParameter("pageNumber");
        
        if(pageNumberStr == null)
            pageNumberStr = "1";
        
        int retailerId = Integer.parseInt((String) request.getAttribute("userId"));
        int pageNumber = Integer.parseInt(pageNumberStr);
        
        try {
            PaginatedListModel<ProductListModel> model = productRepository.getProductsInStockFor(retailerId, pageNumber, 10);
            response.getWriter().write(GSON.toJson(model));
        } catch (SQLException ex) {
            response.setStatus(500);
            ex.printStackTrace(response.getWriter());
        }
        
    }
}


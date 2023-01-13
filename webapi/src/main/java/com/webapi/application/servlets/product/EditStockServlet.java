package com.webapi.application.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.product.EditStockModel;
import com.webapi.application.utils.HttpServletRequestUtils;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.persistence.abstractions.IProductRepository;
import com.webapi.persistence.concretes.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/product/editStock")
public class EditStockServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private final IProductRepository productRepository;

    public EditStockServlet() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        EditStockModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), EditStockModel.class);
        
        response.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);
        
        try {
            if(productRepository.findById(model.getId()) != null) {
                if(!productRepository.editStock(model.getId(), model.getStock())) {
                    response.setStatus(500);
                }
            } else {
                response.getWriter().write(String.valueOf(model.getId()));
                response.setStatus(404);
            }
        } catch (SQLException ex) {
            response.getWriter().write(ex.getMessage());
            
            response.setStatus(500);
        }

    }

}

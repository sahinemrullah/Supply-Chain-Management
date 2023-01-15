package com.webapi.application.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.abstractions.IValidation;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.models.product.CreateProductModel;
import com.webapi.application.utils.HttpServletRequestUtils;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.application.validators.CreateProductModelValidator;
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

@WebServlet(name = "CreateProductServlet", urlPatterns = {"/product/create"})
public class CreateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private IProductRepository productRepository;

    public CreateProductServlet() {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateProductModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), CreateProductModel.class);

        response.setContentType(HttpServletUtils.MEDIA_TYPE_PLAIN_TEXT);

        IValidator validator = new CreateProductModelValidator(model);
        IValidation validationResult = validator.validate();
        if (validationResult.isSucceeded()) {

            try {
                Product product = new Product();
                product.setName(model.getName());
                product.setDescription(model.getDescription());
                product.setPrice(model.getPrice());
                product.setStock(model.getStock());
                product.setProductImages(model.getImagePaths());
                product.setRetailerId(Integer.parseInt((String) request.getAttribute("userId")));
                if (productRepository.add(product)) {
                    response.setStatus(200);
                } else {
                    response.setStatus(500);
                }
            } catch (SQLException ex) {
                response.getWriter().write(request.getParameter("userId"));
                response.setStatus(500);
            }
        } else {
            response.setStatus(400);
            response.getWriter().write(GSON.toJson(validationResult.getValidationErrors()));
        }
    }

}

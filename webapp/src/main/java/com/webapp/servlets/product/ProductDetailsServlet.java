package com.webapp.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.product.ProductDetailsModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.IntegerUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;

@WebServlet(name = "ProductDetailsServlet", urlPatterns = {"/urun/detay"})
public class ProductDetailsServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        
        if(idStr == null || !IntegerUtils.tryParse(idStr))
            response.sendRedirect("/");
        
        String token = (String)request.getSession().getAttribute("token");
        
        HashMap<String, String> parameters = new HashMap<String, String>();
        
        parameters.put("id", idStr);
        
        Response result = HttpRequestUtils.get("http://localhost:9080/product/get", parameters, token);
        
        if(result.getStatusCode() == 200) {
            ProductDetailsModel model = GSON.fromJson(result.getResponseMessage(), ProductDetailsModel.class);
            request.setAttribute("product", model);
            
            request.getRequestDispatcher("/WEB-INF/product/productDetails.jsp").forward(request, response);
        } else {
            response.getWriter().write(result.getResponseMessage());
        }
    }
}

package com.webapp.servlets.product;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.webapp.models.PaginatedListModel;
import com.webapp.models.product.ProductSearchModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.HashMap;

@WebServlet(name = "SearchProductServlet", urlPatterns = {"/urun/ara"})
public class SearchProductServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        String pageNumberStr = request.getParameter("pageNumber");
        
        String token = (String)request.getSession().getAttribute("token");
        
        HashMap<String, String> parameters = new HashMap<>();
        
        parameters.put("query", query);
        parameters.put("pageNumber", pageNumberStr);
        parameters.put("pageSize", "3");
        
        Response result = HttpRequestUtils.get("http://localhost:9080/product/search", parameters, token);
        
        if(result.getStatusCode() == 200) {
            Type type = TypeToken.getParameterized(PaginatedListModel.class, ProductSearchModel.class).getType();
            PaginatedListModel<ProductSearchModel> model = GSON.fromJson(result.getResponseMessage(), type);
            request.setAttribute("model", model);
            request.getRequestDispatcher("/WEB-INF/product/productList.jsp").forward(request, response);
        } else {
            response.setStatus(result.getStatusCode());
        }
    }
}

package com.webapp.servlets.retailer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.webapp.models.PaginatedListModel;
import com.webapp.models.retailer.ProductListModel;
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

@WebServlet(name = "OutOfStockProductListServlet", urlPatterns = {"/tedarikci/stokta-olmayan-urunler"})
public class OutOfStockProductListServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageNumberStr = request.getParameter("pageNumber");
        
        if(pageNumberStr == null)
            pageNumberStr = "1";
        
        String token = (String)request.getSession().getAttribute("token");
        
        HashMap<String, String> parameters = new HashMap<>();
        
        parameters.put("pageNumber", pageNumberStr);
        
        Response result = HttpRequestUtils.get("http://localhost:9080/retailer/outOfStockProducts", parameters, token);
        if(result.getStatusCode() == 200) {
            Type type = TypeToken.getParameterized(PaginatedListModel.class, ProductListModel.class).getType();
            PaginatedListModel<ProductListModel> model = GSON.fromJson(result.getResponseMessage(), type);
            request.setAttribute("model", model);
            request.getRequestDispatcher("/WEB-INF/retailer/myProducts.jsp").forward(request, response);
        } else {
            response.setStatus(result.getStatusCode());
            response.getWriter().write(result.getResponseMessage());
        }
    }
}

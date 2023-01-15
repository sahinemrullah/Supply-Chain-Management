package com.webapp.servlets.supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.webapp.models.PaginatedListModel;
import com.webapp.models.order.PendingOrderModel;
import com.webapp.models.supplier.OrderHistoryModel;
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

@WebServlet(name = "SupplierHomeServlet", urlPatterns = {"/satici/"})
public class SupplierHomeServlet extends HttpServlet {
    
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
        
        Response result = HttpRequestUtils.get("http://localhost:9080/supplier/orders", parameters, token);
        if(result.getStatusCode() == 200) {
            Type type = TypeToken.getParameterized(PaginatedListModel.class, OrderHistoryModel.class).getType();
            PaginatedListModel<OrderHistoryModel> model = GSON.fromJson(result.getResponseMessage(), type);
            request.setAttribute("model", model);
            request.getRequestDispatcher("/WEB-INF/supplier/home.jsp").forward(request, response);
        } else {
            response.setStatus(result.getStatusCode());
            response.getWriter().write(result.getResponseMessage());
        }
    }

}

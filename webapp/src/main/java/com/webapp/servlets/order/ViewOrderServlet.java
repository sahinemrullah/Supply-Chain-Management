package com.webapp.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.models.order.ViewOrderModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.IntegerUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;

@WebServlet(name = "ViewOrderServlet", urlPatterns = {"/siparis/goruntule"})
public class ViewOrderServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        
        if(idStr == null || !IntegerUtils.tryParse(idStr))
            response.sendRedirect("/");
        
        HttpSession session = request.getSession();
        
        String token = (String) session.getAttribute("token");

        HashMap<String, String> parameters = new HashMap<>();
        
        parameters.put("id", idStr);
        
        Response result = HttpRequestUtils.get("http://localhost:9080/order/details", parameters, token);

        if (result.getStatusCode() == 200) {
            
            ViewOrderModel model = GSON.fromJson(result.getResponseMessage(), ViewOrderModel.class);
            request.setAttribute("model", model);
            
            boolean isRetailer = (boolean) session.getAttribute("isRetailer");
            request.setAttribute("isRetailer", isRetailer);
            request.setAttribute("id", idStr);
            
            request.getRequestDispatcher("/WEB-INF/order/details.jsp").forward(request, response);
        } else {
            response.setStatus(result.getStatusCode());
            response.getWriter().write(result.getResponseMessage());
        }
    }
}

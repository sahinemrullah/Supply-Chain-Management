package com.webapi.application.servlets.supplier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.PaginatedListModel;
import com.webapi.application.models.supplier.OrderHistoryModel;
import com.webapi.persistence.abstractions.IOrderRepository;
import com.webapi.persistence.concretes.OrderRepository;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/supplier/orders"})
public class OrderHistoryServlet extends HttpServlet {
    
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    private IOrderRepository orderRepository;
    
    public OrderHistoryServlet() {
        orderRepository = new OrderRepository();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageNumberStr = request.getParameter("pageNumber");
        
        if(pageNumberStr == null)
            pageNumberStr = "1";
        
        int supplierId = Integer.parseInt((String) request.getAttribute("userId"));
        int pageNumber = Integer.parseInt(pageNumberStr);
        
        try {
            PaginatedListModel<OrderHistoryModel> model = orderRepository.getOrderHistory(supplierId, pageNumber, 10);
            response.getWriter().write(GSON.toJson(model));
        } catch (SQLException ex) {
            response.setStatus(500);
            ex.printStackTrace(response.getWriter());
        }
        
    }
}


package com.webapi.application.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.order.ViewOrderModel;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.persistence.abstractions.IOrderRepository;
import com.webapi.persistence.concretes.OrderRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/order/details")
public class ViewOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private IOrderRepository orderRepository;

    public ViewOrderServlet() {
        orderRepository = new OrderRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        
        if(idStr == null)
            response.setStatus(400);

        response.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);
        
        try {
            boolean isRetailer = (boolean) request.getAttribute("isRetailer");
            int userId = Integer.parseInt((String) request.getAttribute("userId"));
            ViewOrderModel order = orderRepository.findById(Integer.parseInt(idStr), userId, isRetailer);
            if (order != null) {
                response.getWriter().write(GSON.toJson(order));
            } else {
                response.setStatus(404);
            }
        } catch (SQLException ex) {
            response.setStatus(500);
            ex.printStackTrace(response.getWriter());
        }
    }

}

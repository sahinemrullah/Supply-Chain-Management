package com.webapi.application.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.order.ConfirmOrderModel;
import com.webapi.application.utils.HttpServletRequestUtils;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.persistence.abstractions.IOrderRepository;
import com.webapi.persistence.concretes.OrderRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order/confirm")
public class ConfirmOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private final IOrderRepository orderRepository;

    public ConfirmOrderServlet() {
        orderRepository = new OrderRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            ConfirmOrderModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), ConfirmOrderModel.class);

            response.setContentType(HttpServletUtils.MEDIA_TYPE_PLAIN_TEXT);
            
            String errorMessage = orderRepository.confirmOrder(model.getId(), Integer.parseInt((String) request.getAttribute("userId")));
            if(errorMessage != null) {
                response.setStatus(400);
                response.getWriter().write(errorMessage);
            }
        } catch (Exception ex) {
                response.setStatus(500);
            ex.printStackTrace(response.getWriter());
        }
    }

}

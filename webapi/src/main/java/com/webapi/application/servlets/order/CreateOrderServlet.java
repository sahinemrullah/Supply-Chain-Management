package com.webapi.application.servlets.order;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.order.CreateOrderModel;
import com.webapi.application.utils.HttpServletRequestUtils;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.domain.entities.Product;
import com.webapi.persistence.abstractions.IProductRepository;
import com.webapi.persistence.abstractions.IOrderRepository;
import com.webapi.persistence.concretes.OrderRepository;
import com.webapi.persistence.concretes.ProductRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/order/create"})
public class CreateOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();
    private IProductRepository productRepository;
    private IOrderRepository orderRepository;

    public CreateOrderServlet() {
        productRepository = new ProductRepository();
        orderRepository = new OrderRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            CreateOrderModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), CreateOrderModel.class);

            response.setContentType(HttpServletUtils.MEDIA_TYPE_PLAIN_TEXT);
            List<Product> products = productRepository.getAll(model.getProducts().keySet().toArray(new Integer[0]));
            if(products.size() == model.getProducts().keySet().size()) {
                Map<Integer, List<Product>> productsByRetailer = products.stream()
                                                                 .collect(Collectors.groupingBy(p -> p.getRetailerId()));
                orderRepository.createOrder(Integer.parseInt((String) request.getAttribute("userId")), productsByRetailer, model.getProducts());
            } else {
                response.setStatus(400);
            }
        } catch (Exception ex) {
                response.setStatus(400);
            ex.printStackTrace(response.getWriter());
        }
    }

}

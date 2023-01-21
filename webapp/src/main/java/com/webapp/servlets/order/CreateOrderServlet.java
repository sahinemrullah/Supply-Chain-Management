package com.webapp.servlets.order;

import com.webapp.models.cart.Cart;
import com.webapp.models.order.CreateOrderModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/order/create"})
public class CreateOrderServlet extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = getCart(request);
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("/sepet");
        } else {
            
            Result result = RequestBuilder.create()
                    .withURL("orders")
                    .withToken(getToken(session))
                    .post(new CreateOrderModel(cart, getUserId(session)));
            
            processResult(result, request, response);
        }
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/tedarikci/");
    }
    
    @Override
    protected String getJSPPage() {
        return "";
    }
}
